package com.smartcampus.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseRequestDTO {

    @NotBlank(
            message = "Course name is required"
    )
    private String courseName;

    @NotBlank(
            message = "Course code is required"
    )
    private String courseCode;

    @NotBlank(
            message = "Department is required"
    )
    private String department;

    @NotNull(
            message = "Credits are required"
    )
    @Min(
            value = 1,
            message = "Credits must be at least 1"
    )
    @Max(
            value = 10,
            message = "Credits must not exceed 10"
    )
    private Integer credits;

    @NotBlank(
            message = "Faculty name is required"
    )
    private String facultyName;

    public CourseRequestDTO() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(
            String courseName
    ) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(
            String courseCode
    ) {
        this.courseCode = courseCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(
            String department
    ) {
        this.department = department;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(
            Integer credits
    ) {
        this.credits = credits;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(
            String facultyName
    ) {
        this.facultyName = facultyName;
    }
}