package uz.ccrew.internetmarket.dto.user;

import lombok.Builder;
import uz.ccrew.internetmarket.enums.UserRole;

@Builder
public record UserDTO(Long id, String login, UserRole role) {
}
