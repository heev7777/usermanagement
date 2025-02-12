package com.example.usermanagement.controllers;

import com.example.usermanagement.models.Reservation;
import com.example.usermanagement.services.ReservationService;
import com.example.usermanagement.dto.ReservationStatusUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
/**
 * Controller for handling reservation-related operations.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    /**
     * Constructor for ReservationController.
     *
     * @param reservationService Service for handling reservations.
     */
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Retrieves a list of all reservations.
     *
     * @return List of reservations.
     */
    @GetMapping
    public List<Reservation> listReservations() {
        return reservationService.listReservations();
    }

    /**
     * Adds a new reservation.
     *
     * @param reservation Reservation to be added.
     * @return Added reservation.
     */
    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    /**
     * Cancels a reservation by ID.
     *
     * @param id ID of the reservation to cancel.
     * @return Response entity indicating success.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id) {
        return reservationService.cancelReservation(id);
    }

    /**
     * Checks room availability within a date range.
     *
     * @param start Start date.
     * @param end   End date.
     * @return List of reservations within the date range.
     */
    @GetMapping("/availability")
    public List<Reservation> checkAvailability(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return reservationService.checkAvailability(start, end);
    }

    /**
     * Verifies if a reservation exists by ID.
     *
     * @param id Reservation ID.
     * @return True if the reservation exists, otherwise false.
     */
    @GetMapping("/verify/{id}")
    public boolean verifyReservation(@PathVariable Long id) {
        return reservationService.verifyReservation(id);
    }

    /**
     * Updates the status of a reservation.
     *
     * @param id           Reservation ID.
     * @param statusUpdate Status update request.
     * @return Response entity indicating success.
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateReservationStatus(@PathVariable Long id, @RequestBody ReservationStatusUpdateRequest statusUpdate) {
        reservationService.updateReservationStatus(id, statusUpdate.getStatus());
        return ResponseEntity.ok("Reservation status updated");
    }
}

