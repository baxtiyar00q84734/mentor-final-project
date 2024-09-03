package org.example.mentorlearningproject.service;

import org.example.mentorlearningproject.dto.request.CourseRequestDTO;
import org.example.mentorlearningproject.dto.response.CourseResponseDTO;
import org.example.mentorlearningproject.entity.Course;
import org.example.mentorlearningproject.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CourseService(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    public String createCourse(CourseRequestDTO courseRequestDTO) {
        Course course = modelMapper.map(courseRequestDTO, Course.class);
        courseRepository.save(course);
        return "Successfully added";
    }

    public List<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CourseResponseDTO.class))
                .toList();
    }

    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow();
        return modelMapper.map(course, CourseResponseDTO.class);
    }
}
