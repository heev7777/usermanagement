package com.example.usermanagement.controllers;

import com.example.usermanagement.models.Payment;
import com.example.usermanagement.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentsController {
    @Autowired
    private PaymentService service;

    @PostMapping("/register")
    public Payment registerPayment(@RequestParam Long reservationId, @RequestParam Double amount) {
        return service.registerPayment(reservationId, amount);
    }

    @GetMapping
    public List<Payment> listAllPayments() {
        return service.listAllPayments();
    }

    @GetMapping("/status/{reservationId}")
    public boolean checkPaymentStatus(@PathVariable Long reservationId) {
        return service.checkPaymentStatus(reservationId);
    }

    @GetMapping("/total/{reservationId}")
    public Double calculateTotalAmount(@PathVariable Long reservationId) {
        return service.calculateTotalAmount(reservationId);
    }
}
