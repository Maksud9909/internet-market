package uz.ccrew.internetmarket.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.ccrew.internetmarket.dto.order.OrderDTO;
import uz.ccrew.internetmarket.entity.Order;
import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderMapper implements Mapper<OrderDTO, OrderDTO, Order> {
    private final UserRepository userRepository;

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        Optional<User> user = userRepository.findById(orderDTO.userId());
        return user.map(value -> Order.builder()
                .totalPrice(orderDTO.totalPrice())
                .status(orderDTO.status())
                .user(value)
                .build()).orElse(null);
    }

    @Override
    public OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .userId(order.getUser().getId())
                .build();
    }
}
