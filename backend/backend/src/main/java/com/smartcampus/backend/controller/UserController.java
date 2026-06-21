package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.UserRequestDTO;
import com.smartcampus.backend.dto.UserResponseDTO;
import com.smartcampus.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(
            @PathVariable Integer id
    ) {

        return userService.getUserById(id);
    }

    @PostMapping
    public String createUser(
            @RequestBody UserRequestDTO requestDTO
    ) {

        return userService.createUser(
                requestDTO
        );
    }

    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable Integer id
    ) {

        return userService.deleteUser(id);
    }
}