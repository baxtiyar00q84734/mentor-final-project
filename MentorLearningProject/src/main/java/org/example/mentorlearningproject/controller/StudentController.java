package org.example.mentorlearningproject.controller;

import org.example.mentorlearningproject.dto.StudentRequestDTO;
import org.example.mentorlearningproject.dto.StudentResponseDTO;
import org.example.mentorlearningproject.entity.Student;
import org.example.mentorlearningproject.exception.StudentNotFoundException;
import org.example.mentorlearningproject.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public StudentResponseDTO createStudent(@RequestBody StudentRequestDTO studentRequestDTO){
        return studentService.createStudent(studentRequestDTO);
    }

    @GetMapping("/get{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id){
        try {
            return studentService.getStudentById(id);
        } catch (StudentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getAll")
    public List<StudentResponseDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PutMapping("/update{id}")
    public StudentResponseDTO updateStudent(@PathVariable Long id,@RequestBody StudentRequestDTO studentRequestDTO){
        try {
            return studentService.updateStudent(id,studentRequestDTO);
        } catch (StudentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete{id}")
    public void deleteById(@PathVariable Long id) throws StudentNotFoundException {
        studentService.deleteStudentById(id);
    }

    // 5 ci task mentor lesson 35 den

    @GetMapping("/search/lastName")
    public List<StudentResponseDTO> searchByLastName(@RequestParam String lastName){
        return studentService.getStudentByLastName(lastName);
    }

    @GetMapping("/search/email")
    public Optional<Student> searchByEmail(@RequestParam String email){
        return studentService.getStudentByEmail(email);
    }
}
