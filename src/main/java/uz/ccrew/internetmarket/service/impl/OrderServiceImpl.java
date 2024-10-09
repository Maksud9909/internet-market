package uz.ccrew.internetmarket.service.impl;

import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.enums.Status;
import uz.ccrew.internetmarket.entity.Order;
import uz.ccrew.internetmarket.repository.OrderProductRepository;
import uz.ccrew.internetmarket.util.AuthUtil;
import uz.ccrew.internetmarket.mapper.OrderMapper;
import uz.ccrew.internetmarket.dto.order.OrderDTO;
import uz.ccrew.internetmarket.service.OrderService;
import uz.ccrew.internetmarket.exp.NotFoundException;
import uz.ccrew.internetmarket.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final AuthUtil authUtil;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    @Override
    public OrderDTO create() {
        User user = authUtil.loadLoggedUser();

        Optional<Order> existingOrder = orderRepository.findByUserAndStatus(user, Status.PENDING);

        if (existingOrder.isPresent()) {
            return orderMapper.toDTO(existingOrder.get());
        }

        Order order = Order.builder()
                .user(user)
                .totalPrice(0.0)
                .status(Status.PENDING)
                .build();
        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO get() {
        User user = authUtil.loadLoggedUser();
        Optional<Order> order = orderRepository.findByUserId(user.getId());
        if (order.isPresent()) {
            return orderMapper.toDTO(order.get());
        }
        throw new NotFoundException("Order not found");
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

    @Transactional
    @Override
    public void delete() {
        User user = authUtil.loadLoggedUser();
        Optional<Order> order = orderRepository.findByUserId(user.getId());
        if (order.isPresent()) {
            orderProductRepository.deleteByOrder(order.get());
            orderRepository.delete(order.get());
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

