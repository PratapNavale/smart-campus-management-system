package com.smartcampus.backend.exception;

import com.smartcampus.backend.dto.ErrorResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<ErrorResponseDTO>
    handleResourceNotFound(
            ResourceNotFoundException ex
    ) {

        ErrorResponseDTO response =
                new ErrorResponseDTO(
                        false,
                        ex.getMessage()
                );

        return ResponseEntity
                .status(
                        HttpStatus.NOT_FOUND
                )
                .body(
                        response
                );
    }

    @ExceptionHandler(
            IllegalArgumentException.class
    )
    public ResponseEntity<ErrorResponseDTO>
    handleIllegalArgument(
            IllegalArgumentException ex
    ) {

        ErrorResponseDTO response =
                new ErrorResponseDTO(
                        false,
                        ex.getMessage()
                );

        return ResponseEntity
                .status(
                        HttpStatus.BAD_REQUEST
                )
                .body(
                        response
                );
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<ErrorResponseDTO>
    handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        String[] fieldPriority = {
                "userId",
                "studentId",
                "courseId",
                "firstName",
                "lastName",
                "email",
                "phone",
                "department",
                "designation",
                "courseName",
                "courseCode",
                "credits",
                "facultyName",
                "attendanceDate",
                "amount",
                "paymentDate",
                "paymentType",
                "roomNumber",
                "block",
                "roomType",
                "status",
                "semester"
        };

        String message = "Validation failed";

        for (String field : fieldPriority) {

            var fieldError =
                    ex.getBindingResult()
                            .getFieldErrors()
                            .stream()
                            .filter(error ->
                                    error.getField()
                                            .equals(field)
                            )
                            .findFirst();

            if (fieldError.isPresent()) {

                message =
                        fieldError.get()
                                .getDefaultMessage();

                break;
            }
        }

        ErrorResponseDTO response =
                new ErrorResponseDTO(
                        false,
                        message
                );

        return ResponseEntity
                .status(
                        HttpStatus.BAD_REQUEST
                )
                .body(
                        response
                );
    }

    @ExceptionHandler(
            RuntimeException.class
    )
    public ResponseEntity<ErrorResponseDTO>
    handleRuntimeException(
            RuntimeException ex
    ) {

        ErrorResponseDTO response =
                new ErrorResponseDTO(
                        false,
                        ex.getMessage()
                );

        return ResponseEntity
                .status(
                        HttpStatus.BAD_REQUEST
                )
                .body(
                        response
                );
    }

    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<ErrorResponseDTO>
    handleGenericException(
            Exception ex
    ) {

        ErrorResponseDTO response =
                new ErrorResponseDTO(
                        false,
                        "Internal Server Error"
                );

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(
                        response
                );
    }
}