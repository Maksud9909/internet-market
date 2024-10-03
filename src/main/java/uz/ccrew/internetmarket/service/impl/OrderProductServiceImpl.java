package uz.ccrew.internetmarket.service.impl;

import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.entity.Order;
import uz.ccrew.internetmarket.util.AuthUtil;
import uz.ccrew.internetmarket.entity.Product;
import uz.ccrew.internetmarket.entity.OrderProducts;
import uz.ccrew.internetmarket.exp.NotFoundException;
import uz.ccrew.internetmarket.mapper.OrderProductMapper;
import uz.ccrew.internetmarket.repository.OrderRepository;
import uz.ccrew.internetmarket.service.OrderProductService;
import uz.ccrew.internetmarket.repository.ProductRepository;
import uz.ccrew.internetmarket.dto.orderProduct.OrderProductDTO;
import uz.ccrew.internetmarket.repository.OrderProductRepository;
import uz.ccrew.internetmarket.dto.orderProduct.OrderProductCreateDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {
    private final OrderProductRepository orderProductRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductMapper orderProductMapper;
    private final AuthUtil authUtil;

    @Override
    public OrderProductDTO addProductToOrder(OrderProductCreateDTO dto) {
        User user = authUtil.loadLoggedUser();
        Order order = orderRepository.findByOrderIdAndUserId(dto.orderId(), user.getId());

        if (order == null) {
            throw new NotFoundException("Order not found");
        }

        Product product = productRepository.loadById(dto.productId(), "Product with id " + dto.productId() + " not found");

        OrderProducts orderProducts = OrderProducts.builder()
                .order(order)
                .product(product)
                .quantity(dto.quantity())
                .price(product.getPrice() * dto.quantity())
                .build();

        double updatedTotalPrice = order.getTotalPrice() + (orderProducts.getPrice() * dto.quantity());
        order.setTotalPrice(updatedTotalPrice);

        orderRepository.save(order);
        orderProductRepository.save(orderProducts);

        return orderProductMapper.toDTO(orderProducts);
    }

    @Override
    public void deleteProductById(Long orderId, Long productId) {
        User user = authUtil.loadLoggedUser();
        Order order = orderRepository.findByOrderIdAndUserId(orderId, user.getId());
        if (order == null) {
            throw new NotFoundException("Order not found");
        }

        Optional<OrderProducts> orderProduct = orderProductRepository.findByOrder_OrderIdAndProduct_ProductId(order.getOrderId(), productId);
        if (orderProduct.isEmpty()) {
            throw new NotFoundException("Product not found in order");
        }

        double updatedTotalPrice = order.getTotalPrice() - (orderProduct.get().getPrice() * orderProduct.get().getQuantity());
        order.setTotalPrice(updatedTotalPrice);

        orderProductRepository.delete(orderProduct.get());
        orderRepository.save(order);
    }
}
