package uz.ccrew.internetmarket.service;

import uz.ccrew.internetmarket.dto.orderProduct.OrderProductDTO;
import uz.ccrew.internetmarket.dto.orderProduct.OrderProductCreateDTO;

public interface OrderProductService {
    OrderProductDTO addProductToOrder(OrderProductCreateDTO dto);

    void deleteProductById(Long orderId,Long productId);
}
