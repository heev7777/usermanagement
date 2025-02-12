package com.example.usermanagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a payment entity associated with a reservation
 */
@Entity
public class Payment {

    /** Unique identifier for the payment*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The id of the reservation associated with a payment*/
    private Long reservationId;

    /** The amount of money paid for the reservation*/
    private Double amount;

    /** Payment status: true if paid, false if pending*/
    private boolean paid;

    /**
     * Default constructor for JPA
     */
    public Payment() {}

    /**
     * Parameterized constructor to initialize a new Payment instance
     *
     * @param reservationId the id of the associated reservation
     * @param amount the amount paid
     * @param paid the payment status (true = paid, false = pending)
     */
    public Payment(Long reservationId, Double amount, boolean paid) {
        this.reservationId = reservationId;
        this.amount = amount;
        this.paid = paid;
    }

    /**
     * Gets the unique identifier of the payment
     *
     * @return the payment id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the reservation id associated with this payment
     *
     * @return the reservation id
     */
    public Long getReservationId() {
        return reservationId;
    }

    /**
     * Sets the reservation id associated with this payment
     *
     * @param reservationId the reservation id to set
     */
    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * Gets the amount paid
     *
     * @return the payment amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount paid
     *
     * @param amount the payment amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Checks if the payment has been completed
     *
     * @return true if paid, false if pending
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Sets the payment status
     *
     * @param paid true if paid, false if pending
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
