package uz.ccrew.internetmarket.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.ccrew.internetmarket.dto.order.OrderCreateDTO;
import uz.ccrew.internetmarket.dto.order.OrderDTO;
import uz.ccrew.internetmarket.entity.Order;
import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.enums.Status;
import uz.ccrew.internetmarket.exp.NotFoundException;
import uz.ccrew.internetmarket.mapper.OrderMapper;
import uz.ccrew.internetmarket.repository.OrderRepository;
import uz.ccrew.internetmarket.service.OrderService;
import uz.ccrew.internetmarket.util.AuthUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final AuthUtil authUtil;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Override
    public OrderDTO create() {
        User user = authUtil.loadLoggedUser();
        Order order = Order.builder()
                .user(user)
                .totalPrice(0.0)
                .status(Status.PENDING)
                .build();
        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO getById(Long id) {
        User user = authUtil.loadLoggedUser();
        Order order = orderRepository.findByOrderIdAndUserId(id, user.getId());
        return orderMapper.toDTO(order);
    }

    @Override
    public Page<OrderDTO> getList(int page, int size) {
        User user = authUtil.loadLoggedUser();
        Pageable pageable = PageRequest.of(page, size, Sort.by("orderId").descending());

        Page<Order> pageObj = orderRepository.findAllByUserId(user.getId(), pageable);

        List<Order> orderList = pageObj.getContent();
        List<OrderDTO> orderDTOList = orderList.stream().map(orderMapper::toDTO).toList();

        return new PageImpl<>(orderDTOList, pageable, orderList.size());
    }

    @Override
    public void delete(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new NotFoundException("Order with id " + id + " not found");
        }
    }

    @Override
    public void changeStatus(Long id, Status status) {
        if (orderRepository.existsById(id)) {
            Order order = orderRepository.findById(id).get();
            order.setStatus(status);
            orderRepository.save(order);
        } else {
            throw new NotFoundException("Order with id " + id + " not found");
        }
    }
}
