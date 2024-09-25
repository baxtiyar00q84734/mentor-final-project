package org.example.mentorlearningproject.config;


import org.example.mentorlearningproject.dto.book.FeignBookRoot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "bookInfoApiClient", url = "https://all-books-api.p.rapidapi.com")
public interface BookInfoFeignClient {

    @GetMapping("/getBooks")
    List<FeignBookRoot> getAllBooks(
            @RequestHeader("x-rapidapi-key") String apiKey,
            @RequestHeader("x-rapidapi-host") String apiHost
    );
}

