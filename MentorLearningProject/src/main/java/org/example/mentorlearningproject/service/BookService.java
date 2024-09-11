package org.example.mentorlearningproject.service;

import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.request.BookRequestDTO;
import org.example.mentorlearningproject.dto.response.BookResponseDTO;
import org.example.mentorlearningproject.entity.Book;
import org.example.mentorlearningproject.entity.Student;
import org.example.mentorlearningproject.exception.BookNotFoundException;
import org.example.mentorlearningproject.exception.StudentNotFoundException;
import org.example.mentorlearningproject.repository.BookRepository;
import org.example.mentorlearningproject.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;



    public String createBook(BookRequestDTO bookRequestDTO) {
        Book book = modelMapper.map(bookRequestDTO, Book.class);
        bookRepository.save(book);
        return "Successfully added";
    }

    public List<BookResponseDTO> getALl() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(b -> modelMapper.map(b, BookResponseDTO.class))
                .toList();
    }

    public List<BookResponseDTO> getBooksByStudentId(Long studentId) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + studentId));
        List<Book> books = bookRepository.findByStudent(student);
        return books.stream()
                .map(b -> modelMapper.map(b, BookResponseDTO.class))
                .toList();
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) throws BookNotFoundException {
        Book exsistingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id " + id));

        modelMapper.map(bookRequestDTO, exsistingBook);
        Book updatedBook = bookRepository.save(exsistingBook);

        return modelMapper.map(updatedBook, BookResponseDTO.class);
    }

    public void deleteBookById(Long id) throws BookNotFoundException {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id " + id);
        }
        bookRepository.deleteById(id);
    }
}
