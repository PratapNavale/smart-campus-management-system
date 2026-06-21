package com.smartcampus.backend.model;

import java.time.LocalDateTime;

public class ActivityLog {

    private Integer activityId;

    private Integer userId;

    private String activityType;

    private String description;

    private LocalDateTime createdAt;

    public ActivityLog() {
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(
            Integer activityId
    ) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(
            Integer userId
    ) {
        this.userId = userId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(
            String activityType
    ) {
        this.activityType = activityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(
            String description
    ) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt
    ) {
        this.createdAt = createdAt;
    }
}