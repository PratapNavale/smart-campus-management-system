package com.smartcampus.backend.controller;

import jakarta.validation.Valid;
import com.smartcampus.backend.dto.StudentRequestDTO;
import com.smartcampus.backend.dto.StudentResponseDTO;
import com.smartcampus.backend.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(
            @PathVariable Integer id
    ) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public String createStudent(
            @Valid
            @RequestBody StudentRequestDTO requestDTO
    ) {
        return studentService.createStudent(requestDTO);
    }

    @PutMapping("/{id}")
    public String updateStudent(
            @PathVariable Integer id,
            @Valid
            @RequestBody StudentRequestDTO requestDTO
    ) {
        return studentService.updateStudent(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(
            @PathVariable Integer id
    ) {
        return studentService.deleteStudent(id);
    }
}