package org.example.mentorlearningproject.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class StudentResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

    private AddressResponseDTO address;
    private List<BookResponseDTO> books;
    private Set<CourseResponseDTO> courses;
}
