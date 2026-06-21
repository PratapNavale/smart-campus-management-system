package com.smartcampus.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FeePaymentRequestDTO {

    @NotNull(
            message = "Student ID is required"
    )
    private Integer studentId;

    @NotNull(
            message = "Amount is required"
    )
    @DecimalMin(
            value = "0.01",
            message = "Amount must be greater than 0"
    )
    private BigDecimal amount;

    @NotNull(
            message = "Payment date is required"
    )
    private LocalDate paymentDate;

    @NotBlank(
            message = "Payment type is required"
    )
    private String paymentType;

    @NotBlank(
            message = "Payment status is required"
    )
    private String status;

    public FeePaymentRequestDTO() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(
            Integer studentId
    ) {
        this.studentId = studentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(
            BigDecimal amount
    ) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(
            LocalDate paymentDate
    ) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(
            String paymentType
    ) {
        this.paymentType = paymentType;
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