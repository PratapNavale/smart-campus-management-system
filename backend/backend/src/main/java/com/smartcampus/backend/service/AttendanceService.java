package com.smartcampus.backend.service;

import com.smartcampus.backend.exception.ResourceNotFoundException;
import com.smartcampus.backend.dto.AttendanceRequestDTO;
import com.smartcampus.backend.dto.AttendanceResponseDTO;
import com.smartcampus.backend.model.Attendance;
import com.smartcampus.backend.repository.AttendanceRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository
            attendanceRepository;

    public AttendanceService(
            AttendanceRepository attendanceRepository
    ) {
        this.attendanceRepository =
                attendanceRepository;
    }

    public List<AttendanceResponseDTO>
    getAllAttendance() {

        return attendanceRepository
                .findAll()
                .stream()
                .map(
                        this::convertToResponseDTO
                )
                .toList();
    }

    public AttendanceResponseDTO
    getAttendanceById(
            Integer id
    ) {

        Attendance attendance =
                attendanceRepository.findById(
                        id
                );

        if (attendance == null) {

            throw new ResourceNotFoundException(
                    "Attendance record not found"
            );
        }

        return convertToResponseDTO(
                attendance
        );
    }

    public String createAttendance(
            AttendanceRequestDTO requestDTO
    ) {

        Attendance attendance =
                new Attendance();

        attendance.setStudentId(
                requestDTO.getStudentId()
        );

        attendance.setCourseId(
                requestDTO.getCourseId()
        );

        attendance.setAttendanceDate(
                requestDTO.getAttendanceDate()
        );

        attendance.setStatus(
                requestDTO.getStatus()
        );

        attendanceRepository.save(
                attendance
        );

        return "Attendance created successfully";
    }

    public String updateAttendance(
            Integer id,
            AttendanceRequestDTO requestDTO
    ) {

        Attendance attendance =
                attendanceRepository.findById(
                        id
                );

        if (attendance == null) {

            throw new ResourceNotFoundException(
                    "Attendance record not found"
            );
        }

        attendance.setStudentId(
                requestDTO.getStudentId()
        );

        attendance.setCourseId(
                requestDTO.getCourseId()
        );

        attendance.setAttendanceDate(
                requestDTO.getAttendanceDate()
        );

        attendance.setStatus(
                requestDTO.getStatus()
        );

        attendanceRepository.update(
                attendance
        );

        return "Attendance updated successfully";
    }

    public String deleteAttendance(
            Integer id
    ) {

        Attendance attendance =
                attendanceRepository.findById(
                        id
                );

        if (attendance == null) {

            throw new ResourceNotFoundException(
                    "Attendance record not found"
            );
        }

        attendanceRepository.delete(
                id
        );

        return "Attendance deleted successfully";
    }

    private AttendanceResponseDTO
    convertToResponseDTO(
            Attendance attendance
    ) {

        AttendanceResponseDTO dto =
                new AttendanceResponseDTO();

        dto.setAttendanceId(
                attendance.getAttendanceId()
        );

        dto.setStudentId(
                attendance.getStudentId()
        );

        dto.setCourseId(
                attendance.getCourseId()
        );

        dto.setAttendanceDate(
                attendance.getAttendanceDate()
        );

        dto.setStatus(
                attendance.getStatus()
        );

        return dto;
    }
}