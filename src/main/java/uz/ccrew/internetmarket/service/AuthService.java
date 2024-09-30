package uz.ccrew.internetmarket.service;

import uz.ccrew.internetmarket.dto.auth.LoginDTO;
import uz.ccrew.internetmarket.dto.auth.LoginResponseDTO;
import uz.ccrew.internetmarket.dto.auth.RegisterDTO;
import uz.ccrew.internetmarket.dto.user.UserDTO;

public interface AuthService {
    UserDTO register(RegisterDTO dto);

    LoginResponseDTO login(LoginDTO loginRequest);

    String refresh();
}
