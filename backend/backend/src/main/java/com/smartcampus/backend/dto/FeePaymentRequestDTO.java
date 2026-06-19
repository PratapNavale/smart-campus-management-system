package com.smartcampus.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FeePaymentRequestDTO {

    private Integer studentId;

    private BigDecimal amount;

    private LocalDate paymentDate;

    private String paymentType;

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
