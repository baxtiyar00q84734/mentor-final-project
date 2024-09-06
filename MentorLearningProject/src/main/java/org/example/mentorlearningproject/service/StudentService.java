package org.example.mentorlearningproject.service;


import org.example.mentorlearningproject.dto.request.StudentRequestDTO;
import org.example.mentorlearningproject.dto.response.StudentResponseDTO;
import org.example.mentorlearningproject.entity.Address;
import org.example.mentorlearningproject.entity.Book;
import org.example.mentorlearningproject.entity.Course;
import org.example.mentorlearningproject.entity.Student;
import org.example.mentorlearningproject.exception.StudentNotFoundException;
import org.example.mentorlearningproject.repository.AddressRepository;
import org.example.mentorlearningproject.repository.BookRepository;
import org.example.mentorlearningproject.repository.CourseRepository;
import org.example.mentorlearningproject.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final BookRepository bookRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, AddressRepository addressRepository, BookRepository bookRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
        this.bookRepository = bookRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student student = modelMapper.map(studentRequestDTO, Student.class);
        Address address = addressRepository.findById(studentRequestDTO.getAddressId()).orElseThrow();

        List<Book> studentBooks = studentRequestDTO.getBooksIds().stream()
                .map(id -> bookRepository.findById(id).orElseThrow())
                .collect(Collectors.toList());

        Set<Course> studentCourses = studentRequestDTO.getCourseIds().stream()
                .map(id -> courseRepository.findById(id).orElseThrow())
                .collect(Collectors.toSet());

        student.setAddress(address);
        student.setBooks(studentBooks);
        student.setCourses(studentCourses);

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponseDTO.class);
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(s -> modelMapper.map(s, StudentResponseDTO.class))
                .toList();
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) throws StudentNotFoundException {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));

        modelMapper.map(studentRequestDTO, existingStudent);
        Student updatedStudent = studentRepository.save(existingStudent);

        return modelMapper.map(updatedStudent, StudentResponseDTO.class);
    }



    public void deleteStudentById(Long id) throws StudentNotFoundException {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }


    public List<StudentResponseDTO> getStudentByLastName(String lastName) {
        List<Student> students = studentRepository.findByLastName(lastName);
        return students.stream()
                .map(s -> modelMapper.map(s, StudentResponseDTO.class))
                .toList();
    }

    public StudentResponseDTO getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow();
        return modelMapper.map(student, StudentResponseDTO.class);
    }
}
