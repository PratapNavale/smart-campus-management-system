package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.DashboardResponseDTO;
import com.smartcampus.backend.service.DashboardService;
import com.smartcampus.backend.dto.ActivityFeedResponseDTO;
import com.smartcampus.backend.service.ActivityLogService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService
            dashboardService;

    private final ActivityLogService
            activityLogService;

    public DashboardController(
            DashboardService dashboardService,
            ActivityLogService activityLogService
    ) {
        this.dashboardService =
                dashboardService;

        this.activityLogService =
                activityLogService;
    }

    @GetMapping("/summary")
    public DashboardResponseDTO
    getDashboardSummary() {

        return dashboardService
                .getDashboardSummary();
    }

    @GetMapping("/activity")
    public List<ActivityFeedResponseDTO>
    getRecentActivities() {

        return activityLogService
                .getRecentActivities();
    }
}