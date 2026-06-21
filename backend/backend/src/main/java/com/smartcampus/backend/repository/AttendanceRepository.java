package com.smartcampus.backend.repository;

import com.smartcampus.backend.model.Attendance;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttendanceRepository {

    private final JdbcTemplate jdbcTemplate;

    public AttendanceRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Attendance>
            attendanceRowMapper =
            (rs, rowNum) -> {

                Attendance attendance =
                        new Attendance();

                attendance.setAttendanceId(
                        rs.getInt(
                                "attendance_id"
                        )
                );

                attendance.setStudentId(
                        rs.getInt(
                                "student_id"
                        )
                );

                attendance.setCourseId(
                        rs.getInt(
                                "course_id"
                        )
                );

                if (rs.getDate(
                        "attendance_date"
                ) != null) {

                    attendance.setAttendanceDate(
                            rs.getDate(
                                    "attendance_date"
                            ).toLocalDate()
                    );
                }

                attendance.setStatus(
                        rs.getString(
                                "status"
                        )
                );

                return attendance;
            };

    public List<Attendance> findAll() {

        String sql = """
                SELECT *
                FROM attendance
                ORDER BY attendance_id
                """;

        return jdbcTemplate.query(
                sql,
                attendanceRowMapper
        );
    }

    public Attendance findById(
            Integer id
    ) {

        String sql = """
                SELECT *
                FROM attendance
                WHERE attendance_id = ?
                """;

        List<Attendance> attendanceList =
                jdbcTemplate.query(
                        sql,
                        attendanceRowMapper,
                        id
                );

        return attendanceList.isEmpty()
                ? null
                : attendanceList.get(0);
    }

    public int save(
            Attendance attendance
    ) {

        String sql = """
                INSERT INTO attendance
                (
                    student_id,
                    course_id,
                    attendance_date,
                    status
                )
                VALUES (?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                attendance.getStudentId(),
                attendance.getCourseId(),
                attendance.getAttendanceDate(),
                attendance.getStatus()
        );
    }

    public int update(
            Attendance attendance
    ) {

        String sql = """
                UPDATE attendance
                SET
                    student_id = ?,
                    course_id = ?,
                    attendance_date = ?,
                    status = ?
                WHERE attendance_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                attendance.getStudentId(),
                attendance.getCourseId(),
                attendance.getAttendanceDate(),
                attendance.getStatus(),
                attendance.getAttendanceId()
        );
    }

    public int delete(
            Integer id
    ) {

        String sql = """
                DELETE FROM attendance
                WHERE attendance_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                id
        );
    }
}