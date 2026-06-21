package com.smartcampus.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HostelRoomRequestDTO {

    @NotNull(
            message = "Student ID is required"
    )
    private Integer studentId;

    @NotBlank(
            message = "Room number is required"
    )
    private String roomNumber;

    @NotBlank(
            message = "Block is required"
    )
    private String block;

    @NotBlank(
            message = "Room type is required"
    )
    private String roomType;

    @NotBlank(
            message = "Room status is required"
    )
    private String status;

    public HostelRoomRequestDTO() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(
            Integer studentId
    ) {
        this.studentId = studentId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(
            String roomNumber
    ) {
        this.roomNumber = roomNumber;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(
            String block
    ) {
        this.block = block;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(
            String roomType
    ) {
        this.roomType = roomType;
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