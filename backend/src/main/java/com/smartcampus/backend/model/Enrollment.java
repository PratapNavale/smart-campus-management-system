package com.smartcampus.backend.model;

import java.time.LocalDate;

public class Enrollment {

    private Integer enrollmentId;
    private Integer studentId;
    private Integer courseId;
    private LocalDate enrollmentDate;
    private String status;

    public Enrollment() {
    }

    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(
            Integer enrollmentId
    ) {
        this.enrollmentId = enrollmentId;
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

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(
            LocalDate enrollmentDate
    ) {
        this.enrollmentDate = enrollmentDate;
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