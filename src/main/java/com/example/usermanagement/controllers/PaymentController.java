package com.example.usermanagement.controllers;

import com.example.usermanagement.models.Payment;
import com.example.usermanagement.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller for managing payment related operations
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

    /** Service layer to handle business logic for payments*/
    private final PaymentService paymentService;

    /**
     * Constructor-based dependency injection for PaymentService
     *
     * @param paymentService the service handling payment operations
     */
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Registers a new payment for a reservation
     *
     * @param reservationId the id of the reservation for which the payment is made
     * @param amount the amount paid for the reservation
     * @return the registered Payment object
     */
    @PostMapping
    public Payment registerPayment(@RequestParam Long reservationId, @RequestParam Double amount) {
        return paymentService.registerPayment(reservationId, amount);
    }

    /**
     * Retrieves a list of all payments
     *
     * @return a list of all registered payments
     */
    @GetMapping
    public List<Payment> listAllPayments() {
        return paymentService.listAllPayments();
    }

    /**
     * Checks the payment status of a reservation
     *
     * @param reservationId the id of the reservation to check
     * @return ResponseEntity containing true if the reservation is paid, false otherwise
     */
    @GetMapping("/status/{reservationId}")
    public ResponseEntity<Boolean> checkPaymentStatus(@PathVariable Long reservationId) {
        boolean isPaid = paymentService.checkPaymentStatus(reservationId);
        return ResponseEntity.ok(isPaid);
    }

    /**
     * Calculates the total amount paid for a specific reservation
     *
     * @param reservationId the id of the reservation
     * @return ResponseEntity containing the total amount paid
     */
    @GetMapping("/total/{reservationId}")
    public ResponseEntity<Double> calculateTotalAmount(@PathVariable Long reservationId) {
        double totalAmount = paymentService.calculateTotalAmount(reservationId);
        return ResponseEntity.ok(totalAmount);
    }
}