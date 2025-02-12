package com.example.usermanagement.repositories;

import com.example.usermanagement.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}