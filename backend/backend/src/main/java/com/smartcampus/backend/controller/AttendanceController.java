package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.AttendanceRequestDTO;
import com.smartcampus.backend.dto.AttendanceResponseDTO;
import com.smartcampus.backend.service.AttendanceService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin("*")
public class AttendanceController {

    private final AttendanceService
            attendanceService;

    public AttendanceController(
            AttendanceService attendanceService
    ) {
        this.attendanceService =
                attendanceService;
    }

    @GetMapping
    public List<AttendanceResponseDTO>
    getAllAttendance() {

        return attendanceService
                .getAllAttendance();
    }

    @GetMapping("/{id}")
    public AttendanceResponseDTO
    getAttendanceById(
            @PathVariable Integer id
    ) {

        return attendanceService
                .getAttendanceById(id);
    }

    @PostMapping
    public String createAttendance(
            @RequestBody
            AttendanceRequestDTO requestDTO
    ) {

        return attendanceService
                .createAttendance(
                        requestDTO
                );
    }

    @PutMapping("/{id}")
    public String updateAttendance(
            @PathVariable Integer id,
            @RequestBody
            AttendanceRequestDTO requestDTO
    ) {

        return attendanceService
                .updateAttendance(
                        id,
                        requestDTO
                );
    }

    @DeleteMapping("/{id}")
    public String deleteAttendance(
            @PathVariable Integer id
    ) {

        return attendanceService
                .deleteAttendance(id);
    }
}