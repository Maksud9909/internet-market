package uz.ccrew.internetmarket.controller;

import org.springframework.web.bind.annotation.*;
import uz.ccrew.internetmarket.dto.Response;
import uz.ccrew.internetmarket.dto.user.UserDTO;
import uz.ccrew.internetmarket.dto.ResponseMaker;
import uz.ccrew.internetmarket.dto.auth.LoginDTO;
import uz.ccrew.internetmarket.service.AuthService;
import uz.ccrew.internetmarket.dto.auth.RegisterDTO;
import uz.ccrew.internetmarket.dto.auth.LoginResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Authorization API")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register User")
    public ResponseEntity<Response<UserDTO>> registerDTO(@RequestBody @Valid RegisterDTO dto) {
        return ResponseMaker.ok(authService.register(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Login User")
    public ResponseEntity<Response<LoginResponseDTO>> login(@RequestBody @Valid LoginDTO loginRequest) {
        return ResponseMaker.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Refresh Access token")
    public ResponseEntity<Response<String>> refresh() {
        return ResponseMaker.ok(authService.refresh());
    }
}