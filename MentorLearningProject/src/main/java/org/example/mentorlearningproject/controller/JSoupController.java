package org.example.mentorlearningproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.service.JSoupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class JSoupController {

    private final JSoupService jSoupService;

    @GetMapping("/home")
    public List<String> getScrapedData() throws IOException {
        return jSoupService.scrapeData();
    }
}
