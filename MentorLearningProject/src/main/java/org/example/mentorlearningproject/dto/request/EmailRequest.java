package org.example.mentorlearningproject.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {

    private String to;
    private String subject;
    private String body;


}