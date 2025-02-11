package com.example.usermanagement.services;

import com.example.usermanagement.models.Reservation;
import com.example.usermanagement.repositories.ReservationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> listReservations() {
        return reservationRepository.findAll();
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public ResponseEntity<String> cancelReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return ResponseEntity.ok("Reservation cancelled successfully");
        }
        return ResponseEntity.badRequest().body("Reservation not found");
    }

    public List<Reservation> checkAvailability(LocalDate start, LocalDate end) {
        return reservationRepository.findByCheckInDateBetween(start, end);
    }

    public boolean verifyReservation(Long id) {
        return reservationRepository.existsById(id);
    }

    public void updateReservationStatus(Long id, ReservationStatus status) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        reservation.ifPresent(r -> {
            r.setStatus(status);
            reservationRepository.save(r);
        });
    }
}