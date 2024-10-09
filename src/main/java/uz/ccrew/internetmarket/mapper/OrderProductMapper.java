package uz.ccrew.internetmarket.mapper;

import uz.ccrew.internetmarket.entity.OrderProducts;
import uz.ccrew.internetmarket.dto.orderProduct.OrderProductDTO;
import uz.ccrew.internetmarket.dto.orderProduct.OrderProductCreateDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProductMapper implements Mapper<OrderProductCreateDTO, OrderProductDTO, OrderProducts> {

    @Override
    public OrderProducts toEntity(OrderProductCreateDTO orderProductCreateDTO) {
        return null;
    }
    

    @Override
    public OrderProductDTO toDTO(OrderProducts orderProduct) {
        return OrderProductDTO.builder()
                .orderId(orderProduct.getId())
                .productName(orderProduct.getProduct().getProductName())
                .productId(orderProduct.getProduct().getProductId())
                .quantity(orderProduct.getQuantity())
                .price(orderProduct.getPrice())
                .build();
    }
}
