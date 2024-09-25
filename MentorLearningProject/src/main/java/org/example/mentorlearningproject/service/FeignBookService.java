package org.example.mentorlearningproject.service;


import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.book.FeignBookRoot;
import org.example.mentorlearningproject.config.BookInfoFeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeignBookService {


    private final BookInfoFeignClient bookInfoFeignClient;

    public List<FeignBookRoot> getAllBooks() {
        String apiKey = "30352bce24msh83ca744080a3c32p184f14jsncf3c1c98dbf2";
        String apiHost = "all-books-api.p.rapidapi.com";

        return bookInfoFeignClient.getAllBooks(apiKey, apiHost);
    }
}



