package com.smartcampus.backend.repository;

import com.smartcampus.backend.model.HostelRoom;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HostelRoomRepository {

    private final JdbcTemplate jdbcTemplate;

    public HostelRoomRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<HostelRoom>
            hostelRoomRowMapper =
            (rs, rowNum) -> {

                HostelRoom room =
                        new HostelRoom();

                room.setRoomId(
                        rs.getInt(
                                "room_id"
                        )
                );

                room.setStudentId(
                        rs.getInt(
                                "student_id"
                        )
                );

                room.setRoomNumber(
                        rs.getString(
                                "room_number"
                        )
                );

                room.setBlock(
                        rs.getString(
                                "block"
                        )
                );

                room.setRoomType(
                        rs.getString(
                                "room_type"
                        )
                );

                room.setStatus(
                        rs.getString(
                                "status"
                        )
                );

                return room;
            };

    public List<HostelRoom> findAll() {

        String sql = """
                SELECT *
                FROM hostel_room
                ORDER BY room_id
                """;

        return jdbcTemplate.query(
                sql,
                hostelRoomRowMapper
        );
    }

    public HostelRoom findById(
            Integer id
    ) {

        String sql = """
                SELECT *
                FROM hostel_room
                WHERE room_id = ?
                """;

        List<HostelRoom> rooms =
                jdbcTemplate.query(
                        sql,
                        hostelRoomRowMapper,
                        id
                );

        return rooms.isEmpty()
                ? null
                : rooms.get(0);
    }

    public int save(
            HostelRoom room
    ) {

        String sql = """
                INSERT INTO hostel_room
                (
                    student_id,
                    room_number,
                    block,
                    room_type,
                    status
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                room.getStudentId(),
                room.getRoomNumber(),
                room.getBlock(),
                room.getRoomType(),
                room.getStatus()
        );
    }

    public int update(
            HostelRoom room
    ) {

        String sql = """
                UPDATE hostel_room
                SET
                    student_id = ?,
                    room_number = ?,
                    block = ?,
                    room_type = ?,
                    status = ?
                WHERE room_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                room.getStudentId(),
                room.getRoomNumber(),
                room.getBlock(),
                room.getRoomType(),
                room.getStatus(),
                room.getRoomId()
        );
    }

    public int delete(
            Integer id
    ) {

        String sql = """
                DELETE FROM hostel_room
                WHERE room_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                id
        );
    }
}