package org.example.mentorlearningproject.repository;

import org.example.mentorlearningproject.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
