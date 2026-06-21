package com.smartcampus.backend.auth;

import com.smartcampus.backend.dto.AuthResponseDTO;
import com.smartcampus.backend.dto.LoginRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody
            LoginRequestDTO requestDTO
    ) {

        System.out.println(
                "CONTROLLER HIT"
        );

        AuthResponseDTO response =
                authService.login(requestDTO);

        System.out.println(response);

        return ResponseEntity.ok(response);
    }
}