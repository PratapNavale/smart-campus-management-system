package com.smartcampus.backend.repository;

import com.smartcampus.backend.model.Role;
import com.smartcampus.backend.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {

        User users = new User();

        users.setUserId(rs.getInt("User_id"));
        users.setUsername(rs.getString("username"));
        users.setPassword(rs.getString("password"));
        users.setRole(
                Role.valueOf(
                        rs.getString("role")
                )
        );

        if (rs.getTimestamp("created_at") != null) {
            users.setCreatedAt(
                    rs.getTimestamp("created_at")
                            .toLocalDateTime()
            );
        }

        return users;
    };

    public Integer saveAndReturnId(User user) {

        String sql = """
            INSERT INTO users
            (
                username,
                password,
                role
            )
            VALUES (?, ?, ?)
            """;

        KeyHolder keyHolder =
                new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps =
                    connection.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    );

            ps.setString(
                    1,
                    user.getUsername()
            );

            ps.setString(
                    2,
                    user.getPassword()
            );

            ps.setString(
                    3,
                    user.getRole().name()
            );

            return ps;

        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public List<User> findAll() {

        String sql = """
                SELECT *
                FROM users
                ORDER BY User_id
                """;

        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User findById(Integer id) {

        String sql = """
                SELECT *
                FROM users
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
                INSERT INTO users
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
                DELETE FROM users
                WHERE User_id = ?
                """;

        return jdbcTemplate.update(sql, id);
    }

    public User findByUsername(String username) {

        String sql = """
            SELECT *
            FROM users
            WHERE username = ?
            """;

        List<User> users =
                jdbcTemplate.query(
                        sql,
                        userRowMapper,
                        username
                );

        return users.isEmpty()
                ? null
                : users.get(0);
    }
}