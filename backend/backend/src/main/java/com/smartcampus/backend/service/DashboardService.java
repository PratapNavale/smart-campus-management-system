package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.DashboardResponseDTO;

import com.smartcampus.backend.repository.DashboardRepository;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final DashboardRepository
            dashboardRepository;

    public DashboardService(
            DashboardRepository dashboardRepository
    ) {
        this.dashboardRepository =
                dashboardRepository;
    }

    public DashboardResponseDTO
    getDashboardSummary() {

        DashboardResponseDTO response =
                new DashboardResponseDTO();

        response.setTotalStudents(
                dashboardRepository
                        .getTotalStudents()
        );

        response.setTotalFaculty(
                dashboardRepository
                        .getTotalFaculty()
        );

        response.setTotalCourses(
                dashboardRepository
                        .getTotalCourses()
        );

        response.setTotalEnrollments(
                dashboardRepository
                        .getTotalEnrollments()
        );

        response.setTotalPayments(
                dashboardRepository
                        .getTotalPayments()
        );

        response.setTotalHostelRooms(
                dashboardRepository
                        .getTotalHostelRooms()
        );

        response.setPresentStudents(
                dashboardRepository
                        .getPresentStudents()
        );

        response.setAbsentStudents(
                dashboardRepository
                        .getAbsentStudents()
        );

        response.setLateStudents(
                dashboardRepository
                        .getLateStudents()
        );

        response.setOccupiedRooms(
                dashboardRepository
                        .getOccupiedRooms()
        );

        response.setVacantRooms(
                dashboardRepository
                        .getVacantRooms()
        );

        response.setTotalRevenue(
                dashboardRepository
                        .getTotalRevenue()
        );

        Integer present =
                response.getPresentStudents();

        Integer absent =
                response.getAbsentStudents();

        Integer late =
                response.getLateStudents();

        int totalAttendanceRecords =
                present + absent + late;

        if (totalAttendanceRecords > 0) {

            double attendancePercentage =
                    ((double) present
                            / totalAttendanceRecords)
                            * 100;

            response.setAttendancePercentage(
                    Math.round(
                            attendancePercentage * 100.0
                    ) / 100.0
            );
        } else {

            response.setAttendancePercentage(
                    0.0
            );
        }

        Integer occupied =
                response.getOccupiedRooms();

        Integer vacant =
                response.getVacantRooms();

        int totalRooms =
                occupied + vacant;

        if (totalRooms > 0) {

            double occupancyPercentage =
                    ((double) occupied
                            / totalRooms)
                            * 100;

            response.setHostelOccupancyPercentage(
                    Math.round(
                            occupancyPercentage * 100.0
                    ) / 100.0
            );
        } else {

            response.setHostelOccupancyPercentage(
                    0.0
            );
        }

        return response;
    }
}