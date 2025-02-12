package com.example.usermanagement.repositories;

import com.example.usermanagement.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Customer, Long> {
}

