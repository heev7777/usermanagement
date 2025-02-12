package com.example.usermanagement.controllers;

import com.example.usermanagement.models.Payment;
import com.example.usermanagement.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment registerPayment(@RequestParam Long reservationId, @RequestParam Double amount) {
        return paymentService.registerPayment(reservationId, amount);
    }

    @GetMapping
    public List<Payment> listAllPayments() {
        return paymentService.listAllPayments();
    }

    @GetMapping("/status/{reservationId}")
    public ResponseEntity<Boolean> checkPaymentStatus(@PathVariable Long reservationId) {
        boolean isPaid = paymentService.checkPaymentStatus(reservationId);
        return ResponseEntity.ok(isPaid);
    }

    @GetMapping("/total/{reservationId}")
    public ResponseEntity<Double> calculateTotalAmount(@PathVariable Long reservationId) {
        double totalAmount = paymentService.calculateTotalAmount(reservationId);
        return ResponseEntity.ok(totalAmount);
    }
}