package uz.ccrew.internetmarket.dto.user;

import uz.ccrew.internetmarket.enums.UserRole;

import lombok.Builder;

@Builder
public record UserDTO(Long id, String login, UserRole role) {
}
