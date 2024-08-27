package org.example.mentorlearningproject.service;


import org.example.mentorlearningproject.dto.StudentRequestDTO;
import org.example.mentorlearningproject.dto.StudentResponseDTO;
import org.example.mentorlearningproject.entity.Student;
import org.example.mentorlearningproject.exception.StudentNotFoundException;
import org.example.mentorlearningproject.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    // 36 lesson yazdiqlarim bundan asagi


    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student student = modelMapper.map(studentRequestDTO, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponseDTO.class);
    }

    public StudentResponseDTO getStudentById(Long id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return modelMapper.map(student.get(), StudentResponseDTO.class);
        } else {
            throw new StudentNotFoundException("Student not found for this id" + id);
        }
    }

    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> studentResponseDTOs = new ArrayList<>();

        for (Student student : students) {
            StudentResponseDTO dto = modelMapper.map(student, StudentResponseDTO.class);
            studentResponseDTOs.add(dto);
        }

        return studentResponseDTOs;

    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) throws StudentNotFoundException {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            //
            modelMapper.map(studentRequestDTO, student); // Map updated fields to existing student
            Student updatedStudent = studentRepository.save(student);
            return modelMapper.map(updatedStudent, StudentResponseDTO.class);
            //
        } else {
            throw new StudentNotFoundException("Student not found for this id" + id);
        }
    }

    public void deleteStudentById(Long id) throws StudentNotFoundException {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentNotFoundException("Student not found for this id" + id);
        }
    }


    public List<StudentResponseDTO> getStudentByLastName(String lastName) {
        List<Student> students = studentRepository.findByLastName(lastName);
        List<StudentResponseDTO> studentResponseDTOs = new ArrayList<>();

        for (Student student : students) {
            StudentResponseDTO dto = modelMapper.map(student, StudentResponseDTO.class);
            studentResponseDTOs.add(dto);
        }

        return studentResponseDTOs;
    }

    public Optional<Student> getStudentByEmail(String email) {
        Optional<Student> student = studentRepository.findByEmail(email);
        return student;
    }
}
