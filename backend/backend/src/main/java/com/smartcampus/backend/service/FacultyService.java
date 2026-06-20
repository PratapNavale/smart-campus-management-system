package com.smartcampus.backend.service;

import com.smartcampus.backend.exception.ResourceNotFoundException;

import com.smartcampus.backend.dto.FacultyRequestDTO;
import com.smartcampus.backend.dto.FacultyResponseDTO;

import com.smartcampus.backend.model.Faculty;

import com.smartcampus.backend.repository.FacultyRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final ActivityLogService activityLogService;

    public FacultyService(
            FacultyRepository facultyRepository,
            ActivityLogService activityLogService
    ) {
        this.facultyRepository =
                facultyRepository;

        this.activityLogService =
                activityLogService;
    }

    public List<FacultyResponseDTO>
    getAllFaculties() {

        return facultyRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public FacultyResponseDTO getFacultyById(
            Integer id
    ) {

        Faculty faculty =
                facultyRepository.findById(id);

        if (faculty == null) {

            throw new ResourceNotFoundException(
                    "Faculty not found"
            );
        }

        return convertToResponseDTO(
                faculty
        );
    }

    public String createFaculty(
            FacultyRequestDTO requestDTO
    ) {

        Faculty faculty =
                new Faculty();

        faculty.setFirstName(
                requestDTO.getFirstName()
        );

        faculty.setLastName(
                requestDTO.getLastName()
        );

        faculty.setEmail(
                requestDTO.getEmail()
        );

        faculty.setDepartment(
                requestDTO.getDepartment()
        );

        faculty.setDesignation(
                requestDTO.getDesignation()
        );

        facultyRepository.save(
                faculty
        );

        activityLogService.logActivity(
                1,
                "FACULTY",
                "Faculty added: "
                        + faculty.getFirstName()
                        + " "
                        + faculty.getLastName()
        );

        return "Faculty created successfully";
    }

    public String updateFaculty(
            Integer id,
            FacultyRequestDTO requestDTO
    ) {

        Faculty faculty =
                new Faculty();

        faculty.setFirstName(
                requestDTO.getFirstName()
        );

        faculty.setLastName(
                requestDTO.getLastName()
        );

        faculty.setEmail(
                requestDTO.getEmail()
        );

        faculty.setDepartment(
                requestDTO.getDepartment()
        );

        faculty.setDesignation(
                requestDTO.getDesignation()
        );

        int updated =
                facultyRepository.update(
                        id,
                        faculty
                );

        if (updated == 0) {

            throw new ResourceNotFoundException(
                    "Faculty not found"
            );
        }

        activityLogService.logActivity(
                1,
                "FACULTY",
                "Faculty updated: "
                        + faculty.getFirstName()
                        + " "
                        + faculty.getLastName()
        );

        return "Faculty updated successfully";
    }

    public String deleteFaculty(
            Integer id
    ) {

        int deleted =
                facultyRepository.delete(id);

        if (deleted == 0) {

            throw new ResourceNotFoundException(
                    "Faculty not found"
            );
        }

        Faculty faculty =
                facultyRepository.findById(id);

        if (faculty == null) {

            throw new ResourceNotFoundException(
                    "Faculty not found"
            );
        }

        return "Faculty deleted successfully";
    }

    private FacultyResponseDTO
    convertToResponseDTO(
            Faculty faculty
    ) {

        FacultyResponseDTO dto =
                new FacultyResponseDTO();

        dto.setFacultyId(
                faculty.getFacultyId()
        );

        dto.setFirstName(
                faculty.getFirstName()
        );

        dto.setLastName(
                faculty.getLastName()
        );

        dto.setEmail(
                faculty.getEmail()
        );

        dto.setDepartment(
                faculty.getDepartment()
        );

        dto.setDesignation(
                faculty.getDesignation()
        );

        dto.setCreatedAt(
                faculty.getCreatedAt()
        );

        return dto;
    }
}