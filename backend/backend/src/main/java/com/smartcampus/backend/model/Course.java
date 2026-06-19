package com.smartcampus.backend.model;

public class Course {

    private Integer courseId;

    private String courseName;

    private String courseCode;

    private String department;

    private Integer credits;

    private String facultyName;

    public Course() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(
            Integer courseId
    ) {
        this.courseId = courseId;
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