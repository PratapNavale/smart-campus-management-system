package com.smartcampus.backend.auth;

import com.smartcampus.backend.dto.AuthResponseDTO;
import com.smartcampus.backend.dto.LoginRequestDTO;
import com.smartcampus.backend.model.User;
import com.smartcampus.backend.repository.UserRepository;
import com.smartcampus.backend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponseDTO login(
            LoginRequestDTO requestDTO
    ) {

        System.out.println("========== LOGIN START ==========");

        System.out.println(
                "Username received: "
                        + requestDTO.getUsername()
        );

        User user =
                userRepository.findByUsername(
                        requestDTO.getUsername()
                );

        System.out.println(
                "User found: "
                        + (user != null)
        );

        if (user == null) {

            throw new RuntimeException(
                    "Invalid username or password"
            );
        }

        System.out.println(
                "DB Username: "
                        + user.getUsername()
        );

        System.out.println(
                "DB Password Hash: "
                        + user.getPassword()
        );

        boolean passwordMatches =
                passwordEncoder.matches(
                        requestDTO.getPassword(),
                        user.getPassword()
                );

        System.out.println(
                "Password Match Result: "
                        + passwordMatches
        );

        if (!passwordMatches) {

            throw new RuntimeException(
                    "Invalid username or password"
            );
        }

        String token =
                jwtService.generateToken(
                        user.getUsername(),
                        user.getRole().name()
                );

        System.out.println(
                "Generated Token: "
                        + token
        );

        System.out.println(
                "========== LOGIN SUCCESS =========="
        );

        return new AuthResponseDTO(token);
    }
}