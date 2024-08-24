package org.example.mentorlearningproject.controller;

import org.example.mentorlearningproject.service.StudentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
}
