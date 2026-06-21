package com.smartcampus.backend.repository;

import com.smartcampus.backend.model.Course;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    public CourseRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Course>
            courseRowMapper =
            (rs, rowNum) -> {

                Course course =
                        new Course();

                course.setCourseId(
                        rs.getInt("course_id")
                );

                course.setCourseName(
                        rs.getString("course_name")
                );

                course.setCourseCode(
                        rs.getString("course_code")
                );

                course.setDepartment(
                        rs.getString("department")
                );

                course.setCredits(
                        rs.getInt("credits")
                );

                course.setFacultyName(
                        rs.getString("faculty_name")
                );

                return course;
            };

    public List<Course> findAll() {

        String sql = """
                SELECT *
                FROM courses
                ORDER BY course_id
                """;

        return jdbcTemplate.query(
                sql,
                courseRowMapper
        );
    }

    public Course findById(
            Integer id
    ) {

        String sql = """
                SELECT *
                FROM courses
                WHERE course_id = ?
                """;

        List<Course> courses =
                jdbcTemplate.query(
                        sql,
                        courseRowMapper,
                        id
                );

        return courses.isEmpty()
                ? null
                : courses.get(0);
    }

    public int save(
            Course course
    ) {

        String sql = """
                INSERT INTO courses
                (
                    course_name,
                    course_code,
                    department,
                    credits,
                    faculty_name
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                course.getCourseName(),
                course.getCourseCode(),
                course.getDepartment(),
                course.getCredits(),
                course.getFacultyName()
        );
    }

    public int update(
            Integer id,
            Course course
    ) {

        String sql = """
                UPDATE courses
                SET
                    course_name = ?,
                    course_code = ?,
                    department = ?,
                    credits = ?,
                    faculty_name = ?
                WHERE course_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                course.getCourseName(),
                course.getCourseCode(),
                course.getDepartment(),
                course.getCredits(),
                course.getFacultyName(),
                id
        );
    }

    public int delete(
            Integer id
    ) {

        String sql = """
                DELETE FROM courses
                WHERE course_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                id
        );
    }
}