package com.smartcampus.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class FacultyRequestDTO {

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
            message = "Department is required"
    )
    private String department;

    @NotBlank(
            message = "Designation is required"
    )
    private String designation;

    public FacultyRequestDTO() {
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}