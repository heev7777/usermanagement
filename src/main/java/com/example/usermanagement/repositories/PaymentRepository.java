package com.example.usermanagement.repositories;

import com.example.usermanagement.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
