package com.example.usermanagement.controllers;

import com.example.usermanagement.models.Reservation;
import com.example.usermanagement.services.ReservationService;
import com.example.usermanagement.dto.ReservationStatusUpdateRequest;
import com.example.usermanagement.models.ReservationStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> listReservations() {
        return reservationService.listReservations();
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id) {
        return reservationService.cancelReservation(id);
    }

    @GetMapping("/availability")
    public List<Reservation> checkAvailability(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return reservationService.checkAvailability(start, end);
    }

    @GetMapping("/verify/{id}")
    public boolean verifyReservation(@PathVariable Long id) {
        return reservationService.verifyReservation(id);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateReservationStatus(@PathVariable Long id, @RequestBody ReservationStatusUpdateDTO statusUpdate) {
        reservationService.updateReservationStatus(id, statusUpdate.getStatus());
        return ResponseEntity.ok("Reservation status updated");
    }
}
