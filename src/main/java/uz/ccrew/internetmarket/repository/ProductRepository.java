package uz.ccrew.internetmarket.repository;

import uz.ccrew.internetmarket.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BasicRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
}
