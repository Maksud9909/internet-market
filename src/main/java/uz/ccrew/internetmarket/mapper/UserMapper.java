package uz.ccrew.internetmarket.mapper;

import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.dto.user.UserDTO;

import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDTO, UserDTO, User> {
    @Override
    public User toEntity(UserDTO dto) {
        return User.builder()
                .login(dto.login())
                .build();
    }

    @Override
    public UserDTO toDTO(User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .role(entity.getRole())
                .build();
    }
}