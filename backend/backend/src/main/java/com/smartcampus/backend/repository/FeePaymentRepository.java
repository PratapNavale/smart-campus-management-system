package com.smartcampus.backend.repository;

import com.smartcampus.backend.model.FeePayment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeePaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    public FeePaymentRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<FeePayment>
            feePaymentRowMapper =
            (rs, rowNum) -> {

                FeePayment payment =
                        new FeePayment();

                payment.setPaymentId(
                        rs.getInt(
                                "payment_id"
                        )
                );

                payment.setStudentId(
                        rs.getInt(
                                "student_id"
                        )
                );

                payment.setAmount(
                        rs.getBigDecimal(
                                "amount"
                        )
                );

                if (rs.getDate(
                        "payment_date"
                ) != null) {

                    payment.setPaymentDate(
                            rs.getDate(
                                    "payment_date"
                            ).toLocalDate()
                    );
                }

                payment.setPaymentType(
                        rs.getString(
                                "payment_type"
                        )
                );

                payment.setStatus(
                        rs.getString(
                                "status"
                        )
                );

                return payment;
            };

    public List<FeePayment> findAll() {

        String sql = """
                SELECT *
                FROM fee_payments
                ORDER BY payment_id
                """;

        return jdbcTemplate.query(
                sql,
                feePaymentRowMapper
        );
    }

    public FeePayment findById(
            Integer id
    ) {

        String sql = """
                SELECT *
                FROM fee_payments
                WHERE payment_id = ?
                """;

        List<FeePayment> payments =
                jdbcTemplate.query(
                        sql,
                        feePaymentRowMapper,
                        id
                );

        return payments.isEmpty()
                ? null
                : payments.get(0);
    }

    public int save(
            FeePayment payment
    ) {

        String sql = """
                INSERT INTO fee_payments
                (
                    student_id,
                    amount,
                    payment_date,
                    payment_type,
                    status
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                payment.getStudentId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentType(),
                payment.getStatus()
        );
    }

    public int update(
            FeePayment payment
    ) {

        String sql = """
                UPDATE fee_payments
                SET
                    student_id = ?,
                    amount = ?,
                    payment_date = ?,
                    payment_type = ?,
                    status = ?
                WHERE payment_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                payment.getStudentId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentType(),
                payment.getStatus(),
                payment.getPaymentId()
        );
    }

    public int delete(
            Integer id
    ) {

        String sql = """
                DELETE FROM fee_payments
                WHERE payment_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                id
        );
    }
}