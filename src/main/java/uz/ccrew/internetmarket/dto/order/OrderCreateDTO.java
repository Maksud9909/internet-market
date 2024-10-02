package uz.ccrew.internetmarket.dto.order;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderCreateDTO(Long userId,
                             List<ProductOrderDTO> products) {}
