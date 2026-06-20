package com.smartcampus.backend.dto;

import java.time.LocalDateTime;

public class ActivityFeedResponseDTO {

    private String activityType;

    private String description;

    private LocalDateTime createdAt;

    public ActivityFeedResponseDTO() {
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