package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.UserRequestDTO;
import com.smartcampus.backend.dto.UserResponseDTO;
import com.smartcampus.backend.exception.ResourceNotFoundException;
import com.smartcampus.backend.model.Role;
import com.smartcampus.backend.model.User;
import com.smartcampus.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public UserResponseDTO getUserById(
            Integer id
    ) {

        User user =
                userRepository.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException(
                    "User not found with ID: " + id
            );
        }

        return convertToResponseDTO(user);
    }

    public String createUser(
            UserRequestDTO requestDTO
    ) {

        validateUser(requestDTO);

        User user =
                convertToEntity(requestDTO);

        userRepository.save(user);

        return "User created successfully";
    }

    public String deleteUser(Integer id) {

        User user =
                userRepository.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException(
                    "User not found with ID: " + id
            );
        }

        userRepository.delete(id);

        return "User deleted successfully";
    }

    private User convertToEntity(
            UserRequestDTO dto
    ) {

        User user = new User();

        user.setUsername(
                dto.getUsername()
        );

        user.setPassword(
                dto.getPassword()
        );

        user.setRole(
                Role.valueOf(
                        dto.getRole()
                )
        );

        return user;
    }

    private UserResponseDTO convertToResponseDTO(
            User user
    ) {

        UserResponseDTO dto =
                new UserResponseDTO();

        dto.setUserId(
                user.getUserId()
        );

        dto.setUsername(
                user.getUsername()
        );

        dto.setRole(
                user.getRole().name()
        );

        dto.setCreatedAt(
                user.getCreatedAt()
        );

        return dto;
    }

    private void validateUser(
            UserRequestDTO dto
    ) {

        if (dto.getUsername() == null
                || dto.getUsername().isBlank()) {

            throw new IllegalArgumentException(
                    "Username is required"
            );
        }

        if (dto.getPassword() == null
                || dto.getPassword().isBlank()) {

            throw new IllegalArgumentException(
                    "Password is required"
            );
        }

        if (dto.getRole() == null
                || dto.getRole().isBlank()) {

            throw new IllegalArgumentException(
                    "Role is required"
            );
        }
    }
}