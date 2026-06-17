package com.smartcampus.backend.repository;

import com.smartcampus.backend.model.Role;
import com.smartcampus.backend.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {

        User user = new User();

        user.setUserId(rs.getInt("User_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(
                Role.valueOf(
                        rs.getString("role")
                )
        );

        if (rs.getTimestamp("created_at") != null) {
            user.setCreatedAt(
                    rs.getTimestamp("created_at")
                            .toLocalDateTime()
            );
        }

        return user;
    };

    public List<User> findAll() {

        String sql = """
                SELECT *
                FROM user
                ORDER BY User_id
                """;

        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User findById(Integer id) {

        String sql = """
                SELECT *
                FROM user
                WHERE User_id = ?
                """;

        List<User> users =
                jdbcTemplate.query(
                        sql,
                        userRowMapper,
                        id
                );

        return users.isEmpty() ? null : users.get(0);
    }

    public int save(User user) {

        String sql = """
                INSERT INTO user
                (
                    username,
                    password,
                    role
                )
                VALUES (?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getPassword(),
                user.getRole().name()
        );
    }

    public int delete(Integer id) {

        String sql = """
                DELETE FROM user
                WHERE User_id = ?
                """;

        return jdbcTemplate.update(sql, id);
    }
}