package com.example.usermanagement.services;

import com.example.usermanagement.models.Payment;
import com.example.usermanagement.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for handling payment-related operations
 */
@Service
public class PaymentService {

    /** Repository for handling Payment entity database operations */
    @Autowired
    private PaymentRepository repository;

    /**
     * Registers a payment for a specific reservation
     *
     * @param reservationId the id of the reservation to associate the payment with
     * @param amount the amount paid for the reservation
     * @return the registered Payment entity
     */
    public Payment registerPayment(Long reservationId, Double amount) {
        Payment payment = new Payment(reservationId, amount, true);
        return repository.save(payment);
    }

    /**
     * Retrieves a list of all registered payments
     *
     * @return a list of Payment objects representing all payments
     */
    public List<Payment> listAllPayments() {
        return repository.findAll();
    }

    /**
     * Checks if a reservation has been paid
     *
     * @param reservationId the id of the reservation
     * @return true if the reservation is marked as paid, false otherwise
     */
    public boolean checkPaymentStatus(Long reservationId) {
        return repository.findAll()
                .stream()
                .filter(payment -> payment.getReservationId().equals(reservationId))
                .anyMatch(Payment::isPaid);
    }

    /**
     * Calculates the total amount paid for a specific reservation
     *
     * @param reservationId the id of the reservation
     * @return the total amount paid
     */
    public Double calculateTotalAmount(Long reservationId) {
        return repository.findAll()
                .stream()
                .filter(payment -> payment.getReservationId().equals(reservationId))
                .mapToDouble(Payment::getAmount)
                .sum();
    }
}
