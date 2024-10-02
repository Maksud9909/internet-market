package uz.ccrew.internetmarket.repository;

import org.springframework.stereotype.Repository;
import uz.ccrew.internetmarket.entity.OrderProducts;

@Repository
public interface OrderProductsRepository extends BasicRepository<OrderProducts, Long> {}
