package uz.ccrew.internetmarket.service.impl;

import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.entity.Order;
import uz.ccrew.internetmarket.enums.Status;
import uz.ccrew.internetmarket.util.AuthUtil;
import uz.ccrew.internetmarket.entity.Product;
import uz.ccrew.internetmarket.entity.OrderProducts;
import uz.ccrew.internetmarket.exp.NotFoundException;
import uz.ccrew.internetmarket.dto.product.ProductDTO;
import uz.ccrew.internetmarket.mapper.OrderProductMapper;
import uz.ccrew.internetmarket.repository.OrderRepository;
import uz.ccrew.internetmarket.service.OrderProductService;
import uz.ccrew.internetmarket.repository.ProductRepository;
import uz.ccrew.internetmarket.dto.orderProduct.OrderProductDTO;
import uz.ccrew.internetmarket.dto.orderProduct.OrderProductList;
import uz.ccrew.internetmarket.repository.OrderProductRepository;
import uz.ccrew.internetmarket.dto.orderProduct.OrderProductCreateDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {
    private final AuthUtil authUtil;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductMapper orderProductMapper;
    private final OrderProductRepository orderProductRepository;

    @Override
    public OrderProductDTO addProductToOrder(OrderProductCreateDTO dto) {
        User user = authUtil.loadLoggedUser();
        Optional<Order> order = orderRepository.findByUserAndStatus(user, Status.PENDING);
//        Order order = orderRepository.findByOrderIdAndUserId(dto.orderId(), user.getId());

        if (order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        Product product = productRepository.loadById(dto.productId(), "Product with id " + dto.productId() + " not found");

        OrderProducts orderProducts = OrderProducts.builder()
                .order(order.get())
                .product(product)
                .quantity(dto.quantity())
                .price(product.getPrice() * dto.quantity())
                .build();

        double updatedTotalPrice = order.get().getTotalPrice() + (orderProducts.getPrice() * dto.quantity());
        order.get().setTotalPrice(updatedTotalPrice);

        orderRepository.save(order.get());
        orderProductRepository.save(orderProducts);

        return orderProductMapper.toDTO(orderProducts);
    }

    @Override
    public void deleteProductById(Long productId) {
        User user = authUtil.loadLoggedUser();
        Optional<Order> order = orderRepository.findByUserId(user.getId());
        if (order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        Optional<OrderProducts> orderProduct = orderProductRepository.findByOrder_OrderIdAndProduct_ProductId(order.get().getOrderId(), productId);
        if (orderProduct.isEmpty()) {
            throw new NotFoundException("Product not found in order");
        }

        double updatedTotalPrice = order.get().getTotalPrice() - (orderProduct.get().getPrice() * orderProduct.get().getQuantity());
        order.get().setTotalPrice(updatedTotalPrice);

        orderProductRepository.delete(orderProduct.get());
        orderRepository.save(order.get());
    }

    @Override
    public OrderProductList findAllProductsInOrder() {
        User user = authUtil.loadLoggedUser();
        Optional<Order> order = orderRepository.findByUserId(user.getId());

        if (order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }


        // Извлекаем все OrderProducts для заказа пользователя
        List<OrderProducts> orderProducts = orderProductRepository.findAllByOrder(order.get());

        // Преобразуем в DTO
        List<ProductDTO> productDTOs = orderProducts.stream()
                .map(orderProduct -> ProductDTO.builder()
                        .productId(orderProduct.getProduct().getProductId())
                        .categoryId(orderProduct.getProduct().getCategory().getCategoryId())
                        .categoryName(orderProduct.getProduct().getCategory().getCategoryName())
                        .productName(orderProduct.getProduct().getProductName())
                        .description(orderProduct.getProduct().getDescription())
                        .quantity(orderProduct.getQuantity()) // Извлекаем количество
                        .price(orderProduct.getPrice()) // Извлекаем цену (цена за всё количество)
                        .build())
                .collect(Collectors.toList());

        return OrderProductList.builder()
                .orderId(order.get().getOrderId())
                .products(productDTOs)
                .build();
    }

}
