package com.smartcampus.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AttendanceRequestDTO {

    @NotNull(
            message = "Student ID is required"
    )
    private Integer studentId;

    @NotNull(
            message = "Course ID is required"
    )
    private Integer courseId;

    @NotNull(
            message = "Attendance date is required"
    )
    private LocalDate attendanceDate;

    @NotBlank(
            message = "Attendance status is required"
    )
    private String status;

    public AttendanceRequestDTO() {
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

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(
            LocalDate attendanceDate
    ) {
        this.attendanceDate = attendanceDate;
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