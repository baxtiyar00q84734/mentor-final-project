package org.example.mentorlearningproject.service;

import org.example.mentorlearningproject.dto.request.BookRequestDTO;
import org.example.mentorlearningproject.dto.response.BookResponseDTO;
import org.example.mentorlearningproject.entity.Book;
import org.example.mentorlearningproject.entity.Student;
import org.example.mentorlearningproject.exception.StudentNotFoundException;
import org.example.mentorlearningproject.repository.BookRepository;
import org.example.mentorlearningproject.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public String createBook(BookRequestDTO bookRequestDTO) {
        Book book = modelMapper.map(bookRequestDTO, Book.class);
        bookRepository.save(book);
        return "Successfully added";
    }

    public List<BookResponseDTO> getBooksByStudentId(Long studentId) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + studentId));
        List<Book> books = bookRepository.findByStudent(student);
        return books.stream()
                .map(b -> modelMapper.map(b, BookResponseDTO.class))
                .toList();
    }
}
