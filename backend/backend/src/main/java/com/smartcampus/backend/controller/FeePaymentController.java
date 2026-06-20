package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.FeePaymentRequestDTO;
import com.smartcampus.backend.dto.FeePaymentResponseDTO;
import com.smartcampus.backend.service.FeePaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class FeePaymentController {

    private final FeePaymentService
            feePaymentService;

    public FeePaymentController(
            FeePaymentService feePaymentService
    ) {
        this.feePaymentService =
                feePaymentService;
    }

    @GetMapping
    public List<FeePaymentResponseDTO>
    getAllPayments() {

        return feePaymentService
                .getAllPayments();
    }

    @GetMapping("/{id}")
    public FeePaymentResponseDTO
    getPaymentById(
            @PathVariable Integer id
    ) {

        return feePaymentService
                .getPaymentById(id);
    }

    @PostMapping
    public String createPayment(
            @Valid
            @RequestBody
            FeePaymentRequestDTO requestDTO
    ) {

        return feePaymentService
                .createPayment(
                        requestDTO
                );
    }

    @PutMapping("/{id}")
    public String updatePayment(
            @PathVariable Integer id,
            @Valid
            @RequestBody
            FeePaymentRequestDTO requestDTO
    ) {

        return feePaymentService
                .updatePayment(
                        id,
                        requestDTO
                );
    }

    @DeleteMapping("/{id}")
    public String deletePayment(
            @PathVariable Integer id
    ) {

        return feePaymentService
                .deletePayment(id);
    }
}