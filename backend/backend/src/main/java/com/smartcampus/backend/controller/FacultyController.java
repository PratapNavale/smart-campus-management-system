package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.FacultyRequestDTO;
import com.smartcampus.backend.dto.FacultyResponseDTO;

import com.smartcampus.backend.service.FacultyService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculties")
@CrossOrigin("*")
public class FacultyController {

    private final FacultyService
            facultyService;

    public FacultyController(
            FacultyService facultyService
    ) {
        this.facultyService =
                facultyService;
    }

    @GetMapping
    public List<FacultyResponseDTO>
    getAllFaculties() {

        return facultyService
                .getAllFaculties();
    }

    @GetMapping("/{id}")
    public FacultyResponseDTO
    getFacultyById(
            @PathVariable Integer id
    ) {

        return facultyService
                .getFacultyById(id);
    }

    @PostMapping
    public String createFaculty(
            @RequestBody
            FacultyRequestDTO requestDTO
    ) {

        return facultyService
                .createFaculty(requestDTO);
    }

    @PutMapping("/{id}")
    public String updateFaculty(
            @PathVariable Integer id,
            @RequestBody
            FacultyRequestDTO requestDTO
    ) {

        return facultyService
                .updateFaculty(
                        id,
                        requestDTO
                );
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(
            @PathVariable Integer id
    ) {

        return facultyService
                .deleteFaculty(id);
    }
}