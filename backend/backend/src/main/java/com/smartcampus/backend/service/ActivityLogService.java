package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.ActivityFeedResponseDTO;
import com.smartcampus.backend.model.ActivityLog;
import com.smartcampus.backend.repository.ActivityLogRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityLogService {

    private final ActivityLogRepository
            activityLogRepository;

    public ActivityLogService(
            ActivityLogRepository activityLogRepository
    ) {
        this.activityLogRepository =
                activityLogRepository;
    }

    public void logActivity(
            Integer userId,
            String activityType,
            String description
    ) {

        activityLogRepository.save(
                userId,
                activityType,
                description
        );
    }

    public List<ActivityFeedResponseDTO>
    getRecentActivities() {

        List<ActivityLog> activities =
                activityLogRepository
                        .findRecentActivities();

        List<ActivityFeedResponseDTO>
                responseList =
                new ArrayList<>();

        for (
                ActivityLog activity
                : activities
        ) {

            ActivityFeedResponseDTO dto =
                    new ActivityFeedResponseDTO();

            dto.setActivityType(
                    activity.getActivityType()
            );

            dto.setDescription(
                    activity.getDescription()
            );

            dto.setCreatedAt(
                    activity.getCreatedAt()
            );

            responseList.add(dto);
        }

        return responseList;
    }
}