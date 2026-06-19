package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.FeePaymentRequestDTO;
import com.smartcampus.backend.dto.FeePaymentResponseDTO;
import com.smartcampus.backend.model.FeePayment;
import com.smartcampus.backend.repository.FeePaymentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeePaymentService {

    private final FeePaymentRepository
            feePaymentRepository;

    public FeePaymentService(
            FeePaymentRepository feePaymentRepository
    ) {
        this.feePaymentRepository =
                feePaymentRepository;
    }

    public List<FeePaymentResponseDTO>
    getAllPayments() {

        return feePaymentRepository
                .findAll()
                .stream()
                .map(
                        this::convertToResponseDTO
                )
                .toList();
    }

    public FeePaymentResponseDTO
    getPaymentById(
            Integer id
    ) {

        FeePayment payment =
                feePaymentRepository.findById(
                        id
                );

        if (payment == null) {

            throw new RuntimeException(
                    "Payment not found"
            );
        }

        return convertToResponseDTO(
                payment
        );
    }

    public String createPayment(
            FeePaymentRequestDTO requestDTO
    ) {

        FeePayment payment =
                new FeePayment();

        payment.setStudentId(
                requestDTO.getStudentId()
        );

        payment.setAmount(
                requestDTO.getAmount()
        );

        payment.setPaymentDate(
                requestDTO.getPaymentDate()
        );

        payment.setPaymentType(
                requestDTO.getPaymentType()
        );

        payment.setStatus(
                requestDTO.getStatus()
        );

        feePaymentRepository.save(
                payment
        );

        return "Payment created successfully";
    }

    public String updatePayment(
            Integer id,
            FeePaymentRequestDTO requestDTO
    ) {

        FeePayment payment =
                feePaymentRepository.findById(
                        id
                );

        if (payment == null) {

            throw new RuntimeException(
                    "Payment not found"
            );
        }

        payment.setStudentId(
                requestDTO.getStudentId()
        );

        payment.setAmount(
                requestDTO.getAmount()
        );

        payment.setPaymentDate(
                requestDTO.getPaymentDate()
        );

        payment.setPaymentType(
                requestDTO.getPaymentType()
        );

        payment.setStatus(
                requestDTO.getStatus()
        );

        feePaymentRepository.update(
                payment
        );

        return "Payment updated successfully";
    }

    public String deletePayment(
            Integer id
    ) {

        FeePayment payment =
                feePaymentRepository.findById(
                        id
                );

        if (payment == null) {

            throw new RuntimeException(
                    "Payment not found"
            );
        }

        feePaymentRepository.delete(
                id
        );

        return "Payment deleted successfully";
    }

    private FeePaymentResponseDTO
    convertToResponseDTO(
            FeePayment payment
    ) {

        FeePaymentResponseDTO dto =
                new FeePaymentResponseDTO();

        dto.setPaymentId(
                payment.getPaymentId()
        );

        dto.setStudentId(
                payment.getStudentId()
        );

        dto.setAmount(
                payment.getAmount()
        );

        dto.setPaymentDate(
                payment.getPaymentDate()
        );

        dto.setPaymentType(
                payment.getPaymentType()
        );

        dto.setStatus(
                payment.getStatus()
        );

        return dto;
    }
}