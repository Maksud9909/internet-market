package uz.ccrew.internetmarket.repository;

import uz.ccrew.internetmarket.entity.User;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BasicRepository<User, Long> {
    Optional<User> findByLogin(String login);
}