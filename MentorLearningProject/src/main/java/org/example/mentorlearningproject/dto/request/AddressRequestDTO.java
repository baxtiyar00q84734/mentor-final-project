package org.example.mentorlearningproject.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDTO {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
