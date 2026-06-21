package com.smartcampus.backend.repository;

import com.smartcampus.backend.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Student> studentRowMapper = (rs, rowNum) -> {
        Student student = new Student();

        student.setStudentId(rs.getInt("student_id"));
        student.setUserId(rs.getInt("user_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setEmail(rs.getString("email"));
        student.setPhone(rs.getString("phone"));
        student.setDepartment(rs.getString("department"));
        student.setSemester(rs.getInt("semester"));

        if (rs.getDate("admission_date") != null) {
            student.setAdmissionDate(
                    rs.getDate("admission_date").toLocalDate()
            );
        }

        return student;
    };

    public List<Student> findAll() {

        String sql = """
                SELECT *
                FROM students
                ORDER BY student_id
                """;

        return jdbcTemplate.query(sql, studentRowMapper);
    }

    public Student findById(Integer id) {

        String sql = """
                SELECT *
                FROM students
                WHERE student_id = ?
                """;

        List<Student> students =
                jdbcTemplate.query(sql, studentRowMapper, id);

        return students.isEmpty() ? null : students.get(0);
    }

    public int save(Student student) {

        String sql = """
                INSERT INTO students
                (
                    user_id,
                    first_name,
                    last_name,
                    email,
                    phone,
                    department,
                    semester
                )
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                student.getUserId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhone(),
                student.getDepartment(),
                student.getSemester()
        );
    }

    public int update(Student student) {

        String sql = """
                UPDATE students
                SET
                    first_name = ?,
                    last_name = ?,
                    email = ?,
                    phone = ?,
                    department = ?,
                    semester = ?
                WHERE student_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhone(),
                student.getDepartment(),
                student.getSemester(),
                student.getStudentId()
        );
    }

    public int delete(Integer id) {

        String sql = """
                DELETE FROM students
                WHERE student_id = ?
                """;

        return jdbcTemplate.update(sql, id);
    }
}