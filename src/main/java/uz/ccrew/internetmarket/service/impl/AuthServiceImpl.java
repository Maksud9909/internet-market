package uz.ccrew.internetmarket.service.impl;

import uz.ccrew.internetmarket.dto.auth.LoginDTO;
import uz.ccrew.internetmarket.dto.auth.LoginResponseDTO;
import uz.ccrew.internetmarket.dto.auth.RegisterDTO;
import uz.ccrew.internetmarket.dto.user.UserDTO;
import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.enums.UserRole;
import uz.ccrew.internetmarket.exp.AlreadyExistException;
import uz.ccrew.internetmarket.mapper.UserMapper;
import uz.ccrew.internetmarket.repository.UserRepository;
import uz.ccrew.internetmarket.security.jwt.JWTService;
import uz.ccrew.internetmarket.security.user.UserDetailsImpl;
import uz.ccrew.internetmarket.service.AuthService;
import uz.ccrew.internetmarket.util.AuthUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthUtil authUtil;


    @Override
    public UserDTO register(RegisterDTO dto) {
        Optional<User> optional = userRepository.findByLogin(dto.login());
        if (optional.isPresent()) {
            throw new AlreadyExistException("Login is already existing");
        }
        User user = User.builder()
                .login(dto.login())
                .password(passwordEncoder.encode(dto.password()))
                .role(UserRole.CUSTOMER)
                .credentialsModifiedDate(LocalDateTime.now())
                .build();

        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public LoginResponseDTO login(final LoginDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.login(), loginRequest.password()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new LoginResponseDTO(jwtService.generateAccessToken(userDetails.getUsername()), jwtService.generateRefreshToken(userDetails.getUsername()));
    }

    @Override
    public String refresh() {
        User user = authUtil.loadLoggedUser();
        return jwtService.generateAccessToken(user.getLogin());
    }
}