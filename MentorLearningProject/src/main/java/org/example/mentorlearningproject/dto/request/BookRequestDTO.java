package org.example.mentorlearningproject.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookRequestDTO {
    private String title;
    private String author;
    private LocalDate publishedDate;
}
