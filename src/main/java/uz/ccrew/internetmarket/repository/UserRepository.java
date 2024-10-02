package uz.ccrew.internetmarket.repository;

import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.enums.UserRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BasicRepository<User, Long> {
    Optional<User> findByLogin(String login);

    @Query("""
            select w.login
              from User w
             where w.role in ('EMPLOYEE','MANAGER')
            """)
    List<String> findEmployeesAndManager();

    Page<User> findByRole(UserRole role, Pageable pageable);
}