package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.CourseRequestDTO;
import com.smartcampus.backend.dto.CourseResponseDTO;
import com.smartcampus.backend.model.Course;
import com.smartcampus.backend.repository.CourseRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(
            CourseRepository courseRepository
    ) {
        this.courseRepository =
                courseRepository;
    }

    public List<CourseResponseDTO>
    getAllCourses() {

        return courseRepository
                .findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public CourseResponseDTO
    getCourseById(
            Integer id
    ) {

        Course course =
                courseRepository.findById(id);

        if (course == null) {

            throw new RuntimeException(
                    "Course not found"
            );
        }

        return convertToResponseDTO(
                course
        );
    }

    public String createCourse(
            CourseRequestDTO requestDTO
    ) {

        Course course =
                new Course();

        course.setCourseName(
                requestDTO.getCourseName()
        );

        course.setCourseCode(
                requestDTO.getCourseCode()
        );

        course.setDepartment(
                requestDTO.getDepartment()
        );

        course.setCredits(
                requestDTO.getCredits()
        );

        course.setFacultyName(
                requestDTO.getFacultyName()
        );

        courseRepository.save(
                course
        );

        return "Course created successfully";
    }

    public String updateCourse(
            Integer id,
            CourseRequestDTO requestDTO
    ) {

        Course existingCourse =
                courseRepository.findById(id);

        if (existingCourse == null) {

            throw new RuntimeException(
                    "Course not found"
            );
        }

        existingCourse.setCourseName(
                requestDTO.getCourseName()
        );

        existingCourse.setCourseCode(
                requestDTO.getCourseCode()
        );

        existingCourse.setDepartment(
                requestDTO.getDepartment()
        );

        existingCourse.setCredits(
                requestDTO.getCredits()
        );

        existingCourse.setFacultyName(
                requestDTO.getFacultyName()
        );

        courseRepository.update(
                id,
                existingCourse
        );

        return "Course updated successfully";
    }

    public String deleteCourse(
            Integer id
    ) {

        Course course =
                courseRepository.findById(id);

        if (course == null) {

            throw new RuntimeException(
                    "Course not found"
            );
        }

        courseRepository.delete(id);

        return "Course deleted successfully";
    }

    private CourseResponseDTO
    convertToResponseDTO(
            Course course
    ) {

        CourseResponseDTO dto =
                new CourseResponseDTO();

        dto.setCourseId(
                course.getCourseId()
        );

        dto.setCourseName(
                course.getCourseName()
        );

        dto.setCourseCode(
                course.getCourseCode()
        );

        dto.setDepartment(
                course.getDepartment()
        );

        dto.setCredits(
                course.getCredits()
        );

        dto.setFacultyName(
                course.getFacultyName()
        );

        return dto;
    }
}