package uz.ccrew.internetmarket.repository;

import org.springframework.stereotype.Repository;
import uz.ccrew.internetmarket.entity.Category;

@Repository
public interface CategoryRepository extends BasicRepository<Category, Long> {

}
