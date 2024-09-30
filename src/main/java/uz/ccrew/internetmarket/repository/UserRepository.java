package uz.ccrew.internetmarket.repository;

import uz.ccrew.internetmarket.entity.User;

import java.util.Optional;

public interface UserRepository extends BasicRepository<User, Long>{
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
}
