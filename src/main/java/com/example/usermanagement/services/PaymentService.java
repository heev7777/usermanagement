package com.example.usermanagement.services;

import com.example.usermanagement.models.*;
import com.example.usermanagement.repositories.PaymentRepository;
import com.example.usermanagement.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    /** Repository for handling Payment entity database operations */
    private final PaymentRepository paymentRepository;
    /** Repository for handling Reservation entity database operations */
    private final ReservationRepository reservationRepository;
    private final NotificationService notificationService;  // Inject NotificationService

    /**
     * Constructor-based dependency injection for PaymentService
     *
     * @param paymentRepository the repository handling payment operations
     * @param reservationRepository the repository handling reservation operations
     */
    @Autowired
    public PaymentService(PaymentRepository paymentRepository, ReservationRepository reservationRepository, NotificationService notificationService) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.notificationService = notificationService;
    }

    /**
     * Registers a payment for a given reservation
     *
     * If the reservation exists, a new payment is created,
     * and the reservation status is updated to PAID
     *
     * @param reservationId the id of the reservation for which the payment is made
     * @param amount the amount paid for the reservation
     * @return the registered Payment object
     * @throws IllegalArgumentException if the reservation does not exist
     */
    public Payment registerPayment(Long reservationId, Double amount) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        Payment payment = new Payment(reservationId, amount, true);
        paymentRepository.save(payment);

        reservation.setStatus(ReservationStatus.PAID);
        reservationRepository.save(reservation);

        // Create a notification
        Notification notification = new Notification(
                "Payment of $" + amount + " received for Reservation ID " + reservationId,
                NotificationType.PAYMENT_REGISTERED
        );

        // Save the notification
        notification.setDate(LocalDateTime.now());
        notificationService.saveNotification(notification);

        return payment;
    }

    /**
     * Retrieves a list of all registered payments
     *
     * @return a list of all payments
     */
    public List<Payment> listAllPayments() {
        return paymentRepository.findAll();
    }

    /**
     * Checks if a reservation has been fully paid
     *
     * @param reservationId the id of the reservation to check
     * @return true if the reservation has a PAID status, false otherwise
     */
    public boolean checkPaymentStatus(Long reservationId) {
        return paymentRepository.findByReservationId(reservationId)
                .stream()
                .anyMatch(Payment::isPaid);
    }

    /**
     * Calculates the total amount paid for a specific reservation
     *
     * @param reservationId the id of the reservation
     * @return the total amount paid
     */
    public double calculateTotalAmount(Long reservationId) {
        return paymentRepository.findByReservationId(reservationId)
                .stream()
                .mapToDouble(Payment::getAmount)
                .sum();
    }
}