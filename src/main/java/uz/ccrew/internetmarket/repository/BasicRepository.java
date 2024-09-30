package uz.ccrew.internetmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import uz.ccrew.internetmarket.exp.NotFoundException;

@NoRepositoryBean
public interface BasicRepository<T, D> extends JpaRepository<T, D> {
    default T loadById(D id,String expMessage) {
        return findById(id).orElseThrow(() -> new NotFoundException(expMessage));
    }
}