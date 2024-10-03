package uz.ccrew.internetmarket.repository;

import uz.ccrew.internetmarket.entity.OrderProducts;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderProductRepository extends BasicRepository<OrderProducts,Long>{
    Optional<OrderProducts> findByOrder_OrderIdAndProduct_ProductId(Long order_orderId, Long product_productId);
}
