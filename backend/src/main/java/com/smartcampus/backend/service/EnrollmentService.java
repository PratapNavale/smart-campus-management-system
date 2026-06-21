package com.smartcampus.backend.service;

import com.smartcampus.backend.exception.ResourceNotFoundException;
import com.smartcampus.backend.dto.EnrollmentRequestDTO;
import com.smartcampus.backend.dto.EnrollmentResponseDTO;
import com.smartcampus.backend.model.Enrollment;
import com.smartcampus.backend.repository.EnrollmentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final ActivityLogService activityLogService;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            ActivityLogService activityLogService
    ) {
        this.enrollmentRepository =
                enrollmentRepository;

        this.activityLogService =
                activityLogService;
    }

    public List<EnrollmentResponseDTO>
    getAllEnrollments() {

        return enrollmentRepository
                .findAll()
                .stream()
                .map(
                        this::convertToResponseDTO
                )
                .toList();
    }

    public EnrollmentResponseDTO
    getEnrollmentById(
            Integer id
    ) {

        Enrollment enrollment =
                enrollmentRepository.findById(
                        id
                );

        if (enrollment == null) {

            throw new ResourceNotFoundException(
                    "Enrollment not found"
            );
        }

        return convertToResponseDTO(
                enrollment
        );
    }

    public String createEnrollment(
            EnrollmentRequestDTO requestDTO
    ) {

        Enrollment enrollment =
                new Enrollment();

        enrollment.setStudentId(
                requestDTO.getStudentId()
        );

        enrollment.setCourseId(
                requestDTO.getCourseId()
        );

        enrollment.setStatus(
                requestDTO.getStatus()
        );

        enrollmentRepository.save(
                enrollment
        );

        activityLogService.logActivity(
                1,
                "ENROLLMENT",
                "Student enrolled in course"
        );

        return "Enrollment created successfully";
    }

    public String updateEnrollment(
            Integer id,
            EnrollmentRequestDTO requestDTO
    ) {

        Enrollment enrollment =
                enrollmentRepository.findById(
                        id
                );

        if (enrollment == null) {

            throw new ResourceNotFoundException(
                    "Enrollment not found"
            );
        }

        enrollment.setStudentId(
                requestDTO.getStudentId()
        );

        enrollment.setCourseId(
                requestDTO.getCourseId()
        );

        enrollment.setStatus(
                requestDTO.getStatus()
        );

        enrollmentRepository.update(
                enrollment
        );

        activityLogService.logActivity(
                1,
                "ENROLLMENT",
                "Enrollment updated"
        );

        return "Enrollment updated successfully";
    }

    public String deleteEnrollment(
            Integer id
    ) {

        Enrollment enrollment =
                enrollmentRepository.findById(
                        id
                );

        if (enrollment == null) {

            throw new ResourceNotFoundException(
                    "Enrollment not found"
            );
        }

        enrollmentRepository.delete(
                id
        );

        activityLogService.logActivity(
                1,
                "ENROLLMENT",
                "Enrollment removed"
        );

        return "Enrollment deleted successfully";
    }

    private EnrollmentResponseDTO
    convertToResponseDTO(
            Enrollment enrollment
    ) {

        EnrollmentResponseDTO dto =
                new EnrollmentResponseDTO();

        dto.setEnrollmentId(
                enrollment.getEnrollmentId()
        );

        dto.setStudentId(
                enrollment.getStudentId()
        );

        dto.setCourseId(
                enrollment.getCourseId()
        );

        dto.setEnrollmentDate(
                enrollment.getEnrollmentDate()
        );

        dto.setStatus(
                enrollment.getStatus()
        );

        return dto;
    }
}