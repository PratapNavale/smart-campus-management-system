package com.smartcampus.backend.repository;

import com.smartcampus.backend.model.Faculty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class FacultyRepository {

    private final JdbcTemplate jdbcTemplate;

    public FacultyRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Faculty>
            facultyRowMapper =
            (rs, rowNum) -> {

                Faculty faculty =
                        new Faculty();

                faculty.setUserId(
                        rs.getInt("user_id")
                );

                faculty.setFirstName(
                        rs.getString("first_name")
                );

                faculty.setLastName(
                        rs.getString("last_name")
                );

                faculty.setEmail(
                        rs.getString("email")
                );

                faculty.setDepartment(
                        rs.getString("department")
                );

                faculty.setDesignation(
                        rs.getString("designation")
                );

                if (
                        rs.getTimestamp(
                                "created_at"
                        ) != null
                ) {

                    faculty.setCreatedAt(
                            rs.getTimestamp(
                                    "created_at"
                            ).toLocalDateTime()
                    );
                }

                return faculty;
            };

    public List<Faculty> findAll() {

        String sql = """
                SELECT *
                FROM faculty
                ORDER BY faculty_id
                """;

        return jdbcTemplate.query(
                sql,
                facultyRowMapper
        );
    }

    public Faculty findById(
            Integer id
    ) {

        String sql = """
                SELECT *
                FROM faculty
                WHERE faculty_id = ?
                """;

        List<Faculty> facultyList =
                jdbcTemplate.query(
                        sql,
                        facultyRowMapper,
                        id
                );

        return facultyList.isEmpty()
                ? null
                : facultyList.get(0);
    }

    public int save(
            Faculty faculty
    ) {

        String sql = """
            INSERT INTO faculty
            (
                user_id,
                first_name,
                last_name,
                email,
                department,
                designation
            )
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        return jdbcTemplate.update(
                sql,
                faculty.getUserId(),
                faculty.getFirstName(),
                faculty.getLastName(),
                faculty.getEmail(),
                faculty.getDepartment(),
                faculty.getDesignation()
        );
    }

    public int update(
            Integer id,
            Faculty faculty
    ) {

        String sql = """
                UPDATE faculty
                SET
                    first_name = ?,
                    last_name = ?,
                    email = ?,
                    department = ?,
                    designation = ?
                WHERE faculty_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                faculty.getFirstName(),
                faculty.getLastName(),
                faculty.getEmail(),
                faculty.getDepartment(),
                faculty.getDesignation(),
                id
        );
    }

    public int delete(
            Integer id
    ) {

        String sql = """
                DELETE FROM faculty
                WHERE faculty_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                id
        );
    }
}