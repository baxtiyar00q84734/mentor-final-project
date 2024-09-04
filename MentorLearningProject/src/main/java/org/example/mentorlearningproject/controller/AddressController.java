package org.example.mentorlearningproject.controller;

import org.example.mentorlearningproject.dto.request.AddressRequestDTO;
import org.example.mentorlearningproject.service.AddressService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/save")
    public String createAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        return addressService.createAddress(addressRequestDTO);
    }
}
