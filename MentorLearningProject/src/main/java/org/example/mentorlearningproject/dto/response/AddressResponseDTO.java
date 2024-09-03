package org.example.mentorlearningproject.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDTO {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
