package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.EnrollmentRequestDTO;
import com.smartcampus.backend.dto.EnrollmentResponseDTO;
import com.smartcampus.backend.model.Enrollment;
import com.smartcampus.backend.repository.EnrollmentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository
            enrollmentRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository
    ) {
        this.enrollmentRepository =
                enrollmentRepository;
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

            throw new RuntimeException(
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

            throw new RuntimeException(
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

            throw new RuntimeException(
                    "Enrollment not found"
            );
        }

        enrollmentRepository.delete(
                id
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