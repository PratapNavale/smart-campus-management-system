package com.smartcampus.backend.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public class DashboardRepository {

    private final JdbcTemplate jdbcTemplate;

    public DashboardRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getTotalStudents() {

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM students",
                Integer.class
        );
    }

    public Integer getTotalFaculty() {

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM faculty",
                Integer.class
        );
    }

    public Integer getTotalCourses() {

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM courses",
                Integer.class
        );
    }

    public Integer getTotalEnrollments() {

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM enrollments",
                Integer.class
        );
    }

    public Integer getTotalPayments() {

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM fee_payments",
                Integer.class
        );
    }

    public Integer getTotalHostelRooms() {

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM hostel_room",
                Integer.class
        );
    }
    public Integer getPresentStudents() {

        return jdbcTemplate.queryForObject(
                """
                SELECT COUNT(*)
                FROM attendance
                WHERE status = 'PRESENT'
                """,
                Integer.class
        );
    }

    public Integer getAbsentStudents() {

        return jdbcTemplate.queryForObject(
                """
                SELECT COUNT(*)
                FROM attendance
                WHERE status = 'ABSENT'
                """,
                Integer.class
        );
    }

    public Integer getLateStudents() {

        return jdbcTemplate.queryForObject(
                """
                SELECT COUNT(*)
                FROM attendance
                WHERE status = 'LATE'
                """,
                Integer.class
        );
    }

    public Integer getOccupiedRooms() {

        return jdbcTemplate.queryForObject(
                """
                SELECT COUNT(*)
                FROM hostel_room
                WHERE status = 'OCCUPIED'
                """,
                Integer.class
        );
    }

    public Integer getVacantRooms() {

        return jdbcTemplate.queryForObject(
                """
                SELECT COUNT(*)
                FROM hostel_room
                WHERE status = 'VACANT'
                """,
                Integer.class
        );
    }

    public BigDecimal getTotalRevenue() {

        BigDecimal revenue =
                jdbcTemplate.queryForObject(
                        """
                        SELECT COALESCE(
                            SUM(amount),
                            0
                        )
                        FROM fee_payments
                        WHERE status = 'PAID'
                        """,
                        BigDecimal.class
                );

        return revenue == null
                ? BigDecimal.ZERO
                : revenue;
    }
}