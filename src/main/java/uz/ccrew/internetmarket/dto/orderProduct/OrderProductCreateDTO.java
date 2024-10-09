package uz.ccrew.internetmarket.dto.orderProduct;

import lombok.Builder;

@Builder
public record OrderProductCreateDTO(Long productId,
                                    Long quantity) {}
