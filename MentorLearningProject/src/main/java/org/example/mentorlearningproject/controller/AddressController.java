package org.example.mentorlearningproject.controller;

import org.example.mentorlearningproject.dto.request.AddressRequestDTO;
import org.example.mentorlearningproject.dto.response.AddressResponseDTO;
import org.example.mentorlearningproject.exception.AddressNotFoundException;
import org.example.mentorlearningproject.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-all")
    public List<AddressResponseDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddressById(@PathVariable Long id) throws AddressNotFoundException {
        addressService.deleteAddressById(id);
    }

    @PutMapping("/update/{id}")
    public String updateAddress(@PathVariable Long id,@RequestBody AddressRequestDTO addressRequestDTO) throws AddressNotFoundException {
        return addressService.updateAddress(id,addressRequestDTO);
    }
}
