package uz.ccrew.internetmarket.repository;

import uz.ccrew.internetmarket.entity.OrderProducts;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductsRepository extends BasicRepository<OrderProducts, Long> {}
