package uz.ccrew.internetmarket.repository;

import uz.ccrew.internetmarket.entity.Product;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BasicRepository<Product, Long> {
}
