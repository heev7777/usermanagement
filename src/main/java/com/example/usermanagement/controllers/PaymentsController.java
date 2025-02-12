package com.example.usermanagement.controllers;

import com.example.usermanagement.models.Payment;
import com.example.usermanagement.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing payment-related operations
 */
 @RestController
@RequestMapping("/payments")
public class PaymentsController {
    /** Service layer to handle business logic for payments */
    @Autowired
    private PaymentService service;

    /**
     * Registers a payment for a given reservation
     *
     * @param reservationId the id of the reservation for which the payment is made
     * @param amount the amount paid for the reservation
     * @return the registered Payment object
     */
    @PostMapping("/register")
    public Payment registerPayment(@RequestParam Long reservationId, @RequestParam Double amount) {
        return service.registerPayment(reservationId, amount);
    }

    /**
     * Retrieves a list of all payments
     *
     * @return a list of all registered payments
     */
    @GetMapping
    public List<Payment> listAllPayments() {
        return service.listAllPayments();
    }

    /**
     * Checks the payment status of a reservation
     *
     * @param reservationId the ID of the reservation to check
     * @return true if the reservation is paid, false otherwise
     */
    @GetMapping("/status/{reservationId}")
    public boolean checkPaymentStatus(@PathVariable Long reservationId) {
        return service.checkPaymentStatus(reservationId);
    }

    /**
     * Calculates the total amount paid for a specific reservation
     *
     * @param reservationId the id of the reservation
     * @return the total amount paid for the reservation
     */
    @GetMapping("/total/{reservationId}")
    public Double calculateTotalAmount(@PathVariable Long reservationId) {
        return service.calculateTotalAmount(reservationId);
    }
}
