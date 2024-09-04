package org.example.mentorlearningproject.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class StudentRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

    private Long addressId;
    private List<Long> booksIds;
    private Set<Long> courseIds;
}
