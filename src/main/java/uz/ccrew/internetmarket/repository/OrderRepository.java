package uz.ccrew.internetmarket.repository;

import uz.ccrew.internetmarket.entity.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BasicRepository<Order, Long> {
    Order findByOrderIdAndUserId(Long orderId, Long userId);

    Page<Order> findAllByUserId(Long userId, Pageable pageable);
}
