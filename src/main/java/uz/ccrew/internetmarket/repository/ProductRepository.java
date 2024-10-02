package uz.ccrew.internetmarket.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import uz.ccrew.internetmarket.entity.Product;

@Repository
public interface ProductRepository extends BasicRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
}
