package org.example.mentorlearningproject.service;

import org.example.mentorlearningproject.dto.request.StudentRequestDTO;
import org.example.mentorlearningproject.dto.response.StudentResponseDTO;
import org.example.mentorlearningproject.entity.Address;
import org.example.mentorlearningproject.entity.Book;
import org.example.mentorlearningproject.entity.Course;
import org.example.mentorlearningproject.entity.Student;
import org.example.mentorlearningproject.repository.AddressRepository;
import org.example.mentorlearningproject.repository.BookRepository;
import org.example.mentorlearningproject.repository.CourseRepository;
import org.example.mentorlearningproject.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testCreateStudent() {
        Address address = new Address();
        address.setId(1L);

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setAddress(address);

        Book book = new Book();
        book.setId(1L);

        Course course = new Course();
        course.setId(1L);

        StudentRequestDTO studentRequestDTO = new StudentRequestDTO();
        studentRequestDTO.setFirstName("John");
        studentRequestDTO.setLastName("Doe");
        studentRequestDTO.setAddressId(1L);
        studentRequestDTO.setBooksIds(List.of(1L));
        studentRequestDTO.setCourseIds(Set.of(1L));

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setFirstName("John");
        studentResponseDTO.setLastName("Doe");

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        when(modelMapper.map(any(StudentRequestDTO.class), any(Class.class))).thenReturn(student);
        when(modelMapper.map(any(Student.class), any(Class.class))).thenReturn(studentResponseDTO);

        StudentResponseDTO result = studentService.createStudent(studentRequestDTO);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    public void testGetStudentById() {
        Long validStudentId = 1L;

        Student student = new Student();
        student.setId(validStudentId);
        student.setFirstName("John");
        student.setLastName("Doe");

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setFirstName("John");
        studentResponseDTO.setLastName("Doe");

        when(studentRepository.findById(validStudentId)).thenReturn(Optional.of(student));
        when(modelMapper.map(student, StudentResponseDTO.class)).thenReturn(studentResponseDTO);


        StudentResponseDTO result = studentService.getStudentById(validStudentId);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());

    }
}
