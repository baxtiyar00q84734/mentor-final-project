package org.example.mentorlearningproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.book.FeignBookRoot;
import org.example.mentorlearningproject.service.FeignBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class FeignBookController {
    private final FeignBookService feignBookService;

    @GetMapping("/books")
    public List<FeignBookRoot> getAllBooks() {
        return feignBookService.getAllBooks();
    }
}
