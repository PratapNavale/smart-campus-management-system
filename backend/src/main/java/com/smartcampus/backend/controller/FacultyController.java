package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.FacultyRequestDTO;
import com.smartcampus.backend.dto.FacultyResponseDTO;
import jakarta.validation.Valid;
import com.smartcampus.backend.service.FacultyService;
import org.springframework.web.bind.annotation.*;
import com.smartcampus.backend.dto.FacultyRegistrationRequestDTO;
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

    @PostMapping("/register")
    public String registerFaculty(
            @Valid
            @RequestBody
            FacultyRegistrationRequestDTO dto
    ) {

        return facultyService
                .registerFaculty(dto);
    }

    @PostMapping
    public String createFaculty(
            @Valid
            @RequestBody
            FacultyRequestDTO requestDTO
    ) {

        return facultyService
                .createFaculty(requestDTO);
    }

    @PutMapping("/{id}")
    public String updateFaculty(
            @PathVariable Integer id,
            @Valid
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