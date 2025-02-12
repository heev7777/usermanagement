package com.example.usermanagement.dto;

import com.example.usermanagement.models.ReservationStatus;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for updating reservation status.
 */
public class ReservationStatusUpdateRequest {
    @NotNull(message = "Status is required")
    private ReservationStatus status;

    /**
     * Default constructor.
     */
    public ReservationStatusUpdateRequest() {}

    /**
     * Constructor with parameters.
     *
     * @param status Reservation status.
     */
    public ReservationStatusUpdateRequest(ReservationStatus status) {
        this.status = status;
    }

    /**
     * Gets the reservation status.
     *
     * @return Reservation status.
     */
    public ReservationStatus getStatus() {
        return status;
    }

    /**
     * Sets the reservation status.
     *
     * @param status New reservation status.
     */
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
