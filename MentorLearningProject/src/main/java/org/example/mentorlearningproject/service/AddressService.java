package org.example.mentorlearningproject.service;

import org.example.mentorlearningproject.dto.request.AddressRequestDTO;
import org.example.mentorlearningproject.dto.response.AddressResponseDTO;
import org.example.mentorlearningproject.entity.Address;
import org.example.mentorlearningproject.exception.AddressNotFoundException;
import org.example.mentorlearningproject.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AddressResponseDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(s -> modelMapper.map(s, AddressResponseDTO.class))
                .toList();
    }

    public void deleteAddressById(Long id) throws AddressNotFoundException {
        if (!addressRepository.existsById(id)) {
            throw new AddressNotFoundException("Address not found with id " + id);
        }
        addressRepository.deleteById(id);
    }

    public String updateAddress(Long id, AddressRequestDTO addressRequestDTO) throws AddressNotFoundException {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with id " + id));

        modelMapper.map(addressRequestDTO, existingAddress);
        addressRepository.save(existingAddress);
        return "Successfully updated";
    }


}
