package org.example.mentorlearningproject.controller;

import org.example.mentorlearningproject.dto.request.CourseRequestDTO;
import org.example.mentorlearningproject.dto.response.CourseResponseDTO;
import org.example.mentorlearningproject.exception.CourseNotFoundException;
import org.example.mentorlearningproject.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/save")
    public String createCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        return courseService.createCourse(courseRequestDTO);
    }

    @GetMapping("/get-all")
    public List<CourseResponseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/get/{id}")
    public CourseResponseDTO getCourseById(@PathVariable Long id) throws CourseNotFoundException {
        return courseService.getCourseById(id);
    }


    @PutMapping("/update/{id}")
    public CourseResponseDTO updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO courseRequestDTO) throws CourseNotFoundException {
        return courseService.updateCourse(id, courseRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCourseById(@PathVariable Long id) throws CourseNotFoundException {
        courseService.deleteCourseById(id);
    }
}
