package com.smartcampus.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FeePayment {

    private Integer paymentId;

    private Integer studentId;

    private BigDecimal amount;

    private LocalDate paymentDate;

    private String paymentType;

    private String status;

    public FeePayment() {
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(
            Integer paymentId
    ) {
        this.paymentId = paymentId;
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