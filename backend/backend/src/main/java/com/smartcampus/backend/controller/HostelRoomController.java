package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.HostelRoomRequestDTO;
import com.smartcampus.backend.dto.HostelRoomResponseDTO;
import com.smartcampus.backend.service.HostelRoomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostel-rooms")
@CrossOrigin("*")
public class HostelRoomController {

    private final HostelRoomService
            hostelRoomService;

    public HostelRoomController(
            HostelRoomService hostelRoomService
    ) {
        this.hostelRoomService =
                hostelRoomService;
    }

    @GetMapping
    public List<HostelRoomResponseDTO>
    getAllRooms() {

        return hostelRoomService
                .getAllRooms();
    }

    @GetMapping("/{id}")
    public HostelRoomResponseDTO
    getRoomById(
            @PathVariable Integer id
    ) {

        return hostelRoomService
                .getRoomById(id);
    }

    @PostMapping
    public String createRoom(
            @Valid
            @RequestBody
            HostelRoomRequestDTO requestDTO
    ) {

        return hostelRoomService
                .createRoom(
                        requestDTO
                );
    }

    @PutMapping("/{id}")
    public String updateRoom(
            @PathVariable Integer id,
            @Valid
            @RequestBody
            HostelRoomRequestDTO requestDTO
    ) {

        return hostelRoomService
                .updateRoom(
                        id,
                        requestDTO
                );
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(
            @PathVariable Integer id
    ) {

        return hostelRoomService
                .deleteRoom(id);
    }
}