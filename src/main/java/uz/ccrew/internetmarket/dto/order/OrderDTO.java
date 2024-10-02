package uz.ccrew.internetmarket.dto.order;

import lombok.Builder;
import uz.ccrew.internetmarket.enums.Status;

@Builder
public record OrderDTO(Long userId,
                       Double totalPrice,
                       Status status) {}
