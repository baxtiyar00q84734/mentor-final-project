package org.example.mentorlearningproject.service;

import org.example.mentorlearningproject.dto.request.AddressRequestDTO;
import org.example.mentorlearningproject.dto.request.CourseRequestDTO;
import org.example.mentorlearningproject.entity.Address;
import org.example.mentorlearningproject.entity.Course;
import org.example.mentorlearningproject.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressService(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    public String createAddress(AddressRequestDTO addressRequestDTO) {
        Address address = modelMapper.map(addressRequestDTO, Address.class);
        addressRepository.save(address);
        return "Successfully added";
    }


}
