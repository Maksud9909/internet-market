package uz.ccrew.internetmarket.repository;

import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.enums.Status;
import uz.ccrew.internetmarket.entity.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface OrderRepository extends BasicRepository<Order, Long> {

    Optional<Order> findByUserId(Long userId);

    Page<Order> findAllByUserId(Long userId, Pageable pageable);

    Optional<Order> findByUserAndStatus(User user, Status status);
}
