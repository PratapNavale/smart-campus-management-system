package com.smartcampus.backend.dto;

public class HostelRoomRequestDTO {

    private Integer studentId;

    private String roomNumber;

    private String block;

    private String roomType;

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