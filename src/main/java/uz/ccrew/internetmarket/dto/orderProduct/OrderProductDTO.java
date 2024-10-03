package uz.ccrew.internetmarket.dto.orderProduct;

import lombok.Builder;

@Builder
public record OrderProductDTO(Long orderId,
                              Long productId,
                              String productName,
                              Long quantity,
                              Double price) {}
