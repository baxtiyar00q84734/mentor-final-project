package org.example.mentorlearningproject.controller;

import org.example.mentorlearningproject.dto.request.BookRequestDTO;
import org.example.mentorlearningproject.dto.response.BookResponseDTO;
import org.example.mentorlearningproject.exception.StudentNotFoundException;
import org.example.mentorlearningproject.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/save")
    public String createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.createBook(bookRequestDTO);
    }

    @GetMapping("/student/{studentId}")
    public List<BookResponseDTO> getBooksByStudentId(@PathVariable Long studentId) throws StudentNotFoundException {
        return bookService.getBooksByStudentId(studentId);
    }
}
