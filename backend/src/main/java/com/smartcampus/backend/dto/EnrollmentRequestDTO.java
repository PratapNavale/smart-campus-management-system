package com.smartcampus.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EnrollmentRequestDTO {

    @NotNull(
            message = "Student ID is required"
    )
    private Integer studentId;

    @NotNull(
            message = "Course ID is required"
    )
    private Integer courseId;

    @NotBlank(
            message = "Enrollment status is required"
    )
    private String status;

    public EnrollmentRequestDTO() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(
            Integer studentId
    ) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(
            Integer courseId
    ) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status
    ) {
        this.status = status;
    }
}