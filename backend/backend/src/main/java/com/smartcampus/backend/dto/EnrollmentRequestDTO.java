package com.smartcampus.backend.dto;

public class EnrollmentRequestDTO {

    private Integer studentId;
    private Integer courseId;
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