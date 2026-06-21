package com.smartcampus.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentRequestDTO {

    @NotNull(
            message = "User ID is required"
    )
    private Integer userId;

    @NotBlank(
            message = "First name is required"
    )
    private String firstName;

    @NotBlank(
            message = "Last name is required"
    )
    private String lastName;

    @NotBlank(
            message = "Email is required"
    )
    @Email(
            message = "Invalid email format"
    )
    private String email;

    @NotBlank(
            message = "Phone number is required"
    )
    private String phone;

    @NotBlank(
            message = "Department is required"
    )
    private String department;

    @NotNull(
            message = "Semester is required"
    )
    @Min(
            value = 1,
            message = "Semester must be at least 1"
    )
    @Max(
            value = 8,
            message = "Semester must be at most 8"
    )
    private Integer semester;

    public StudentRequestDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}