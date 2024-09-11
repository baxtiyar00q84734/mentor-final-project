package org.example.mentorlearningproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.request.BookRequestDTO;
import org.example.mentorlearningproject.dto.response.BookResponseDTO;
import org.example.mentorlearningproject.exception.BookNotFoundException;
import org.example.mentorlearningproject.exception.StudentNotFoundException;
import org.example.mentorlearningproject.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping("/save")
    public String createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.createBook(bookRequestDTO);
    }

    @GetMapping("/get-all")
    public List<BookResponseDTO> getALl() {
        return bookService.getALl();
    }

    @GetMapping("/student/{studentId}")
    public List<BookResponseDTO> getBooksByStudentId(@PathVariable Long studentId) throws StudentNotFoundException {
        return bookService.getBooksByStudentId(studentId);
    }

    @PutMapping("/update/{id}")
    public BookResponseDTO updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) throws BookNotFoundException {
        return bookService.updateBook(id, bookRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBookById(@PathVariable Long id) throws BookNotFoundException {
        bookService.deleteBookById(id);
    }
}
