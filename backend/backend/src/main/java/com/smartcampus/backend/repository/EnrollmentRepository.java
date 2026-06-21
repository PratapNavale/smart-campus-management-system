package com.smartcampus.backend.repository;

import com.smartcampus.backend.model.Enrollment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnrollmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public EnrollmentRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Enrollment>
            enrollmentRowMapper =
            (rs, rowNum) -> {

                Enrollment enrollment =
                        new Enrollment();

                enrollment.setEnrollmentId(
                        rs.getInt(
                                "enrollment_id"
                        )
                );

                enrollment.setStudentId(
                        rs.getInt(
                                "student_id"
                        )
                );

                enrollment.setCourseId(
                        rs.getInt(
                                "course_id"
                        )
                );

                if (rs.getDate(
                        "enrollment_date"
                ) != null) {

                    enrollment.setEnrollmentDate(
                            rs.getDate(
                                    "enrollment_date"
                            ).toLocalDate()
                    );
                }

                enrollment.setStatus(
                        rs.getString(
                                "status"
                        )
                );

                return enrollment;
            };

    public List<Enrollment> findAll() {

        String sql = """
                SELECT *
                FROM enrollments
                ORDER BY enrollment_id
                """;

        return jdbcTemplate.query(
                sql,
                enrollmentRowMapper
        );
    }

    public Enrollment findById(
            Integer id
    ) {

        String sql = """
                SELECT *
                FROM enrollments
                WHERE enrollment_id = ?
                """;

        List<Enrollment> enrollments =
                jdbcTemplate.query(
                        sql,
                        enrollmentRowMapper,
                        id
                );

        return enrollments.isEmpty()
                ? null
                : enrollments.get(0);
    }

    public int save(
            Enrollment enrollment
    ) {

        String sql = """
                INSERT INTO enrollments
                (
                    student_id,
                    course_id,
                    status
                )
                VALUES (?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                enrollment.getStudentId(),
                enrollment.getCourseId(),
                enrollment.getStatus()
        );
    }

    public int update(
            Enrollment enrollment
    ) {

        String sql = """
                UPDATE enrollments
                SET
                    student_id = ?,
                    course_id = ?,
                    status = ?
                WHERE enrollment_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                enrollment.getStudentId(),
                enrollment.getCourseId(),
                enrollment.getStatus(),
                enrollment.getEnrollmentId()
        );
    }

    public int delete(
            Integer id
    ) {

        String sql = """
                DELETE FROM enrollments
                WHERE enrollment_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                id
        );
    }
}