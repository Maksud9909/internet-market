package uz.ccrew.internetmarket.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ccrew.internetmarket.entity.Order;
import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.enums.Status;
import uz.ccrew.internetmarket.exp.NotFoundException;
import uz.ccrew.internetmarket.repository.OrderRepository;
import uz.ccrew.internetmarket.service.PaymentService;
import uz.ccrew.internetmarket.util.AuthUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {


    private final AuthUtil authUtil;
    private final OrderRepository orderRepository;

    @Override
    public void pay() {
        User user = authUtil.loadLoggedUser();

        Optional<Order> order = orderRepository.findByUserId(user.getId());

        if (order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        order.get().setStatus(Status.PAID);
        orderRepository.save(order.get());
    }
}
