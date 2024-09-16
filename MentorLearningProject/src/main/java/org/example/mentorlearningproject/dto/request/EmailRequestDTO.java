package org.example.mentorlearningproject.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestDTO {

    @NotNull(message = "To address must not be null")
    @NotEmpty(message = "To address must not be empty")
    @Email(message = "To address must be a valid email")
    private String to;
    private String subject;
    private String body;


    public EmailRequestDTO(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}