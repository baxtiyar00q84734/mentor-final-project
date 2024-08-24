package org.example.mentorlearningproject.service;

import org.example.mentorlearningproject.dto.StudentDTO;
import org.example.mentorlearningproject.entity.Student;
import org.example.mentorlearningproject.exception.StudentNotFoundException;
import org.example.mentorlearningproject.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setDateOfBirth(studentDTO.getDateOfBirth());

        student = studentRepository.save(student);

        StudentDTO savedStudentDTO = new StudentDTO();
        savedStudentDTO.setId(student.getId());
        savedStudentDTO.setFirstName(student.getFirstName());
        savedStudentDTO.setLastName(student.getLastName());
        savedStudentDTO.setEmail(student.getEmail());
        savedStudentDTO.setDateOfBirth(student.getDateOfBirth());

        return savedStudentDTO;
    }

    public StudentDTO getStudentById(Long id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setDateOfBirth(student.getDateOfBirth());

        return studentDTO;
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOs = new ArrayList<>();

        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setFirstName(student.getFirstName());
            studentDTO.setLastName(student.getLastName());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setDateOfBirth(student.getDateOfBirth());

            studentDTOs.add(studentDTO);
        }

        return studentDTOs;
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) throws StudentNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));

        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setDateOfBirth(studentDTO.getDateOfBirth());

        student = studentRepository.save(student);

        StudentDTO updatedStudentDTO = new StudentDTO();
        updatedStudentDTO.setId(student.getId());
        updatedStudentDTO.setFirstName(student.getFirstName());
        updatedStudentDTO.setLastName(student.getLastName());
        updatedStudentDTO.setEmail(student.getEmail());
        updatedStudentDTO.setDateOfBirth(student.getDateOfBirth());

        return updatedStudentDTO;
    }

    public void deleteStudentById(Long id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));

        studentRepository.delete(student);
    }

}
