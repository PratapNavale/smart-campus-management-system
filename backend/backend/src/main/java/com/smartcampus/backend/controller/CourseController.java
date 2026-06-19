package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.CourseRequestDTO;
import com.smartcampus.backend.dto.CourseResponseDTO;
import com.smartcampus.backend.service.CourseService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin("*")
public class CourseController {

    private final CourseService courseService;

    public CourseController(
            CourseService courseService
    ) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseResponseDTO>
    getAllCourses() {

        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseResponseDTO
    getCourseById(
            @PathVariable Integer id
    ) {

        return courseService.getCourseById(
                id
        );
    }

    @PostMapping
    public String createCourse(
            @RequestBody
            CourseRequestDTO requestDTO
    ) {

        return courseService.createCourse(
                requestDTO
        );
    }

    @PutMapping("/{id}")
    public String updateCourse(
            @PathVariable Integer id,
            @RequestBody
            CourseRequestDTO requestDTO
    ) {

        return courseService.updateCourse(
                id,
                requestDTO
        );
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(
            @PathVariable Integer id
    ) {

        return courseService.deleteCourse(
                id
        );
    }
}