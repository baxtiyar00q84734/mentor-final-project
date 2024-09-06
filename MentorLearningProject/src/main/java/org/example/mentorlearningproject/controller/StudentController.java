package org.example.mentorlearningproject.controller;

import org.example.mentorlearningproject.dto.request.StudentRequestDTO;
import org.example.mentorlearningproject.dto.response.StudentResponseDTO;
import org.example.mentorlearningproject.exception.StudentNotFoundException;
import org.example.mentorlearningproject.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public StudentResponseDTO createStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        return studentService.createStudent(studentRequestDTO);
    }

    @GetMapping("/get{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);

    }

    @GetMapping("/getAll")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/update{id}")
    public StudentResponseDTO updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO studentRequestDTO) throws StudentNotFoundException {
        return studentService.updateStudent(id, studentRequestDTO);

    }

    @DeleteMapping("/delete{id}")
    public void deleteById(@PathVariable Long id) throws StudentNotFoundException {
        studentService.deleteStudentById(id);
    }


    @GetMapping("/search/lastName")
    public List<StudentResponseDTO> searchByLastName(@RequestParam String lastName) {
        return studentService.getStudentByLastName(lastName);
    }

    @GetMapping("/search/email")
    public StudentResponseDTO searchByEmail(@RequestParam String email) {
        return studentService.getStudentByEmail(email);
    }
}
