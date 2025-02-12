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
     * @param paymentRepository       the repository responsible for handling payment operations
     * @param reservationRepository   the repository responsible for managing reservation operations
     * @param notificationService     the service responsible for sending notifications related to payments
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
     * This method processes a payment by adding it to the system, calculating the total amount paid
     * so far, and updating the reservation status accordingly. If the total paid amount is equal to
     * or greater than the total reservation cost, the status is updated to PAID; otherwise,
     * it remains PENDING. A notification is also generated to inform about the payment
     *
     * @param reservationId the id of the reservation for which the payment is made
     * @param amount the amount paid for the reservation
     * @return the registered Payment object
     * @throws IllegalArgumentException if the reservation does not exist
     */
    public Payment registerPayment(Long reservationId, Double amount) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        // Save new payment
        Payment payment = new Payment(reservationId, amount, true);
        paymentRepository.save(payment);

        // Calculate total amount paid so far
        double totalPaid = calculateTotalAmount(reservationId);

        // Calculate total cost of reservation
        double totalCost = calculateReservationCost(reservation);

        // Update reservation status only if fully paid
        if (totalPaid >= totalCost) {
            reservation.setStatus(ReservationStatus.PAID);
        } else {
            reservation.setStatus(ReservationStatus.PENDING);
        }
        reservationRepository.save(reservation);

        // Create a notification
        String message = (totalPaid >= totalCost)
                ? "Full payment of $" + totalPaid + " received for Reservation ID " + reservationId
                : "Partial payment of $" + amount + " received for Reservation ID " + reservationId +
                ". Total paid: $" + totalPaid + ", remaining: $" + (totalCost - totalPaid);

        Notification notification = new Notification(message, NotificationType.PAYMENT_REGISTERED);
        notification.setDate(LocalDateTime.now());
        notificationService.saveNotification(notification);

        return payment;
    }

    /**
     * Calculates the total cost of a reservation
     *
     * The total cost is computed based on the number of nights the reservation spans
     * and the price per night of the assigned room
     *
     * @param reservation the Reservation object whose cost needs to be calculated
     * @return the total cost of the reservation
     */
    private double calculateReservationCost(Reservation reservation) {
        long nights = java.time.temporal.ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
        return reservation.getRoom().getPricePerNight() * nights;
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