package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.StudentRequestDTO;
import com.smartcampus.backend.dto.StudentResponseDTO;
import com.smartcampus.backend.exception.ResourceNotFoundException;
import com.smartcampus.backend.model.Student;
import com.smartcampus.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ActivityLogService activityLogService;

    public StudentService(
            StudentRepository studentRepository,
            ActivityLogService activityLogService
    ) {
        this.studentRepository =
                studentRepository;

        this.activityLogService =
                activityLogService;
    }
    public List<StudentResponseDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public StudentResponseDTO getStudentById(Integer id) {

        Student student = studentRepository.findById(id);

        if (student == null) {
            throw new ResourceNotFoundException(
                    "Student not found with ID: " + id
            );
        }

        return convertToResponseDTO(student);
    }

    public String createStudent(StudentRequestDTO requestDTO) {

        validateStudent(requestDTO);

        Student student = convertToEntity(requestDTO);

        studentRepository.save(student);

        activityLogService.logActivity(
                1,
                "STUDENT",
                "Student registered: "
                        + student.getFirstName()
                        + " "
                        + student.getLastName()
        );

        return "Student created successfully";
    }

    public String updateStudent(
            Integer id,
            StudentRequestDTO requestDTO
    ) {

        Student existingStudent =
                studentRepository.findById(id);

        if (existingStudent == null) {
            throw new ResourceNotFoundException(
                    "Student not found with ID: " + id
            );
        }

        validateStudent(requestDTO);

        existingStudent.setFirstName(
                requestDTO.getFirstName()
        );

        existingStudent.setLastName(
                requestDTO.getLastName()
        );

        existingStudent.setEmail(
                requestDTO.getEmail()
        );

        existingStudent.setPhone(
                requestDTO.getPhone()
        );

        existingStudent.setDepartment(
                requestDTO.getDepartment()
        );

        existingStudent.setSemester(
                requestDTO.getSemester()
        );

        studentRepository.update(existingStudent);

        activityLogService.logActivity(
                1,
                "STUDENT",
                "Student updated: "
                        + existingStudent.getFirstName()
                        + " "
                        + existingStudent.getLastName()
        );

        return "Student updated successfully";
    }

    public String deleteStudent(Integer id) {

        Student student =
                studentRepository.findById(id);

        if (student == null) {
            throw new ResourceNotFoundException(
                    "Student not found with ID: " + id
            );
        }

        studentRepository.delete(id);

        activityLogService.logActivity(
                1,
                "STUDENT",
                "Student deleted: "
                        + student.getFirstName()
                        + " "
                        + student.getLastName()
        );

        return "Student deleted successfully";
    }

    private Student convertToEntity(
            StudentRequestDTO dto
    ) {

        Student student = new Student();

        student.setUserId(dto.getUserId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setDepartment(dto.getDepartment());
        student.setSemester(dto.getSemester());

        return student;
    }

    private StudentResponseDTO convertToResponseDTO(
            Student student
    ) {

        StudentResponseDTO dto =
                new StudentResponseDTO();

        dto.setStudentId(student.getStudentId());
        dto.setUserId(student.getUserId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setDepartment(student.getDepartment());
        dto.setSemester(student.getSemester());
        dto.setAdmissionDate(
                student.getAdmissionDate()
        );

        return dto;
    }

    private void validateStudent(
            StudentRequestDTO dto
    ) {

        if (dto.getUserId() == null) {
            throw new IllegalArgumentException(
                    "User ID is required"
            );
        }

        if (dto.getFirstName() == null
                || dto.getFirstName().isBlank()) {

            throw new IllegalArgumentException(
                    "First name is required"
            );
        }

        if (dto.getLastName() == null
                || dto.getLastName().isBlank()) {

            throw new IllegalArgumentException(
                    "Last name is required"
            );
        }

        if (dto.getEmail() == null
                || dto.getEmail().isBlank()) {

            throw new IllegalArgumentException(
                    "Email is required"
            );
        }
    }
}