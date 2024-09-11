package org.example.mentorlearningproject.service;

import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.request.CourseRequestDTO;
import org.example.mentorlearningproject.dto.response.CourseResponseDTO;
import org.example.mentorlearningproject.entity.Course;
import org.example.mentorlearningproject.exception.CourseNotFoundException;
import org.example.mentorlearningproject.exception.StudentNotFoundException;
import org.example.mentorlearningproject.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;



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

    public CourseResponseDTO getCourseById(Long id) throws CourseNotFoundException {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Student not found with id " + id));
        return modelMapper.map(course, CourseResponseDTO.class);
    }

    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO) throws CourseNotFoundException {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + id));

        modelMapper.map(courseRequestDTO, existingCourse);
        Course updatedCourse = courseRepository.save(existingCourse);

        return modelMapper.map(updatedCourse, CourseResponseDTO.class);
    }

    public void deleteCourseById(Long id) throws CourseNotFoundException {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Course not found with id " + id);
        }
        courseRepository.deleteById(id);
    }
}
