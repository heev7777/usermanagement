package com.example.usermanagement.services;

import com.example.usermanagement.models.Payment;
import com.example.usermanagement.models.Reservation;
import com.example.usermanagement.models.ReservationStatus;
import com.example.usermanagement.repositories.PaymentRepository;
import com.example.usermanagement.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, ReservationRepository reservationRepository) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
    }

    public Payment registerPayment(Long reservationId, Double amount) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        Payment payment = new Payment(reservationId, amount, true);
        paymentRepository.save(payment);

        reservation.setStatus(ReservationStatus.PAID);
        reservationRepository.save(reservation);

        return payment;
    }

    public List<Payment> listAllPayments() {
        return paymentRepository.findAll();
    }

    public boolean checkPaymentStatus(Long reservationId) {
        return paymentRepository.findByReservationId(reservationId)
                .stream()
                .anyMatch(Payment::isPaid);
    }

    public double calculateTotalAmount(Long reservationId) {
        return paymentRepository.findByReservationId(reservationId)
                .stream()
                .mapToDouble(Payment::getAmount)
                .sum();
    }
}