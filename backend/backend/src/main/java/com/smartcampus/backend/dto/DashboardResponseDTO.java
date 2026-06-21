package com.smartcampus.backend.dto;

import java.math.BigDecimal;

public class DashboardResponseDTO {

    private Integer totalStudents;
    private Integer totalFaculty;
    private Integer totalCourses;
    private Integer totalEnrollments;
    private Integer totalPayments;
    private Integer totalHostelRooms;

    private Integer presentStudents;
    private Integer absentStudents;
    private Integer lateStudents;

    private Integer occupiedRooms;
    private Integer vacantRooms;

    private BigDecimal totalRevenue;

    private Double attendancePercentage;
    private Double hostelOccupancyPercentage;

    public DashboardResponseDTO() {
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getTotalFaculty() {
        return totalFaculty;
    }

    public void setTotalFaculty(Integer totalFaculty) {
        this.totalFaculty = totalFaculty;
    }

    public Integer getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(Integer totalCourses) {
        this.totalCourses = totalCourses;
    }

    public Integer getTotalEnrollments() {
        return totalEnrollments;
    }

    public void setTotalEnrollments(Integer totalEnrollments) {
        this.totalEnrollments = totalEnrollments;
    }

    public Integer getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Integer totalPayments) {
        this.totalPayments = totalPayments;
    }

    public Integer getTotalHostelRooms() {
        return totalHostelRooms;
    }

    public void setTotalHostelRooms(Integer totalHostelRooms) {
        this.totalHostelRooms = totalHostelRooms;
    }

    public Integer getPresentStudents() {
        return presentStudents;
    }

    public void setPresentStudents(Integer presentStudents) {
        this.presentStudents = presentStudents;
    }

    public Integer getAbsentStudents() {
        return absentStudents;
    }

    public void setAbsentStudents(Integer absentStudents) {
        this.absentStudents = absentStudents;
    }

    public Integer getLateStudents() {
        return lateStudents;
    }

    public void setLateStudents(Integer lateStudents) {
        this.lateStudents = lateStudents;
    }

    public Integer getOccupiedRooms() {
        return occupiedRooms;
    }

    public void setOccupiedRooms(Integer occupiedRooms) {
        this.occupiedRooms = occupiedRooms;
    }

    public Integer getVacantRooms() {
        return vacantRooms;
    }

    public void setVacantRooms(Integer vacantRooms) {
        this.vacantRooms = vacantRooms;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(Double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public Double getHostelOccupancyPercentage() {
        return hostelOccupancyPercentage;
    }

    public void setHostelOccupancyPercentage(
            Double hostelOccupancyPercentage
    ) {
        this.hostelOccupancyPercentage =
                hostelOccupancyPercentage;
    }
}