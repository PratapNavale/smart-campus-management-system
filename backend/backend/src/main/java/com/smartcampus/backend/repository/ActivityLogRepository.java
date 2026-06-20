package com.smartcampus.backend.repository;

import com.smartcampus.backend.model.ActivityLog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityLogRepository {

    private final JdbcTemplate jdbcTemplate;

    public ActivityLogRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ActivityLog>
            activityRowMapper =
            (rs, rowNum) -> {

                ActivityLog activity =
                        new ActivityLog();

                activity.setActivityId(
                        rs.getInt("activity_id")
                );

                activity.setUserId(
                        rs.getInt("user_id")
                );

                activity.setActivityType(
                        rs.getString("activity_type")
                );

                activity.setDescription(
                        rs.getString("description")
                );

                activity.setCreatedAt(
                        rs.getTimestamp(
                                "created_at"
                        ).toLocalDateTime()
                );

                return activity;
            };

    public int save(
            Integer userId,
            String activityType,
            String description
    ) {

        String sql = """
                INSERT INTO activity_logs
                (
                    user_id,
                    activity_type,
                    description
                )
                VALUES (?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                userId,
                activityType,
                description
        );
    }

    public List<ActivityLog> findRecentActivities() {

        String sql = """
                SELECT *
                FROM activity_logs
                ORDER BY created_at DESC
                LIMIT 20
                """;

        return jdbcTemplate.query(
                sql,
                activityRowMapper
        );
    }
}