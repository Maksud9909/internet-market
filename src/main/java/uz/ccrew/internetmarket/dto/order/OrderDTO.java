package uz.ccrew.internetmarket.dto.order;

import uz.ccrew.internetmarket.enums.Status;

import lombok.Builder;

@Builder
public record OrderDTO(Long userId,
                       Long orderId,
                       Double totalPrice,
                       Status status) {}
