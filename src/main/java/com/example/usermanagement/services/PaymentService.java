package com.example.usermanagement.services;

import com.example.usermanagement.models.Payment;
import com.example.usermanagement.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment registerPayment(Long reservationId, Double amount) {
        Payment payment = new Payment(reservationId, amount, true);
        return repository.save(payment);
    }

    public List<Payment> listAllPayments() {
        return repository.findAll();
    }

    public boolean checkPaymentStatus(Long reservationId) {
        return repository.findByReservationId(reservationId).stream().anyMatch(Payment::isPaid);
    }

    public Double calculateTotalAmount(Long reservationId) {
        return repository.findByReservationId(reservationId).stream().mapToDouble(Payment::getAmount).sum();
    }
}
