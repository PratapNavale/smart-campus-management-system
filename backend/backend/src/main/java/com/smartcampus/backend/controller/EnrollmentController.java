package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.EnrollmentRequestDTO;
import com.smartcampus.backend.dto.EnrollmentResponseDTO;
import com.smartcampus.backend.service.EnrollmentService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin("*")
public class EnrollmentController {

    private final EnrollmentService
            enrollmentService;

    public EnrollmentController(
            EnrollmentService enrollmentService
    ) {
        this.enrollmentService =
                enrollmentService;
    }

    @GetMapping
    public List<EnrollmentResponseDTO>
    getAllEnrollments() {

        return enrollmentService
                .getAllEnrollments();
    }

    @GetMapping("/{id}")
    public EnrollmentResponseDTO
    getEnrollmentById(
            @PathVariable Integer id
    ) {

        return enrollmentService
                .getEnrollmentById(id);
    }

    @PostMapping
    public String createEnrollment(
            @RequestBody
            EnrollmentRequestDTO requestDTO
    ) {

        return enrollmentService
                .createEnrollment(
                        requestDTO
                );
    }

    @PutMapping("/{id}")
    public String updateEnrollment(
            @PathVariable Integer id,
            @RequestBody
            EnrollmentRequestDTO requestDTO
    ) {

        return enrollmentService
                .updateEnrollment(
                        id,
                        requestDTO
                );
    }

    @DeleteMapping("/{id}")
    public String deleteEnrollment(
            @PathVariable Integer id
    ) {

        return enrollmentService
                .deleteEnrollment(id);
    }
}