package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.HostelRoomRequestDTO;
import com.smartcampus.backend.dto.HostelRoomResponseDTO;
import com.smartcampus.backend.model.HostelRoom;
import com.smartcampus.backend.repository.HostelRoomRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostelRoomService {

    private final HostelRoomRepository
            hostelRoomRepository;

    public HostelRoomService(
            HostelRoomRepository hostelRoomRepository
    ) {
        this.hostelRoomRepository =
                hostelRoomRepository;
    }

    public List<HostelRoomResponseDTO>
    getAllRooms() {

        return hostelRoomRepository
                .findAll()
                .stream()
                .map(
                        this::convertToResponseDTO
                )
                .toList();
    }

    public HostelRoomResponseDTO
    getRoomById(
            Integer id
    ) {

        HostelRoom room =
                hostelRoomRepository.findById(
                        id
                );

        if (room == null) {

            throw new RuntimeException(
                    "Room not found"
            );
        }

        return convertToResponseDTO(
                room
        );
    }

    public String createRoom(
            HostelRoomRequestDTO requestDTO
    ) {

        HostelRoom room =
                new HostelRoom();

        room.setStudentId(
                requestDTO.getStudentId()
        );

        room.setRoomNumber(
                requestDTO.getRoomNumber()
        );

        room.setBlock(
                requestDTO.getBlock()
        );

        room.setRoomType(
                requestDTO.getRoomType()
        );

        room.setStatus(
                requestDTO.getStatus()
        );

        hostelRoomRepository.save(
                room
        );

        return "Room created successfully";
    }

    public String updateRoom(
            Integer id,
            HostelRoomRequestDTO requestDTO
    ) {

        HostelRoom room =
                hostelRoomRepository.findById(
                        id
                );

        if (room == null) {

            throw new RuntimeException(
                    "Room not found"
            );
        }

        room.setStudentId(
                requestDTO.getStudentId()
        );

        room.setRoomNumber(
                requestDTO.getRoomNumber()
        );

        room.setBlock(
                requestDTO.getBlock()
        );

        room.setRoomType(
                requestDTO.getRoomType()
        );

        room.setStatus(
                requestDTO.getStatus()
        );

        hostelRoomRepository.update(
                room
        );

        return "Room updated successfully";
    }

    public String deleteRoom(
            Integer id
    ) {

        HostelRoom room =
                hostelRoomRepository.findById(
                        id
                );

        if (room == null) {

            throw new RuntimeException(
                    "Room not found"
            );
        }

        hostelRoomRepository.delete(
                id
        );

        return "Room deleted successfully";
    }

    private HostelRoomResponseDTO
    convertToResponseDTO(
            HostelRoom room
    ) {

        HostelRoomResponseDTO dto =
                new HostelRoomResponseDTO();

        dto.setRoomId(
                room.getRoomId()
        );

        dto.setStudentId(
                room.getStudentId()
        );

        dto.setRoomNumber(
                room.getRoomNumber()
        );

        dto.setBlock(
                room.getBlock()
        );

        dto.setRoomType(
                room.getRoomType()
        );

        dto.setStatus(
                room.getStatus()
        );

        return dto;
    }
}