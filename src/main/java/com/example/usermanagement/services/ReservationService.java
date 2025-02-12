package com.example.usermanagement.services;

import com.example.usermanagement.models.Reservation;
import com.example.usermanagement.models.ReservationStatus;
import com.example.usermanagement.models.Room;
import com.example.usermanagement.repositories.ReservationRepository;
import com.example.usermanagement.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for handling reservation operations.
 */
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * Adds a new reservation.
     *
     * @param reservation Reservation to add.
     * @return Saved reservation.
     */
    public Reservation addReservation(Reservation reservation) {
        Room room = reservation.getRoom();
        if (!roomRepository.existsById(room.getRoomId())) {
            roomRepository.save(room);
        }
        return reservationRepository.save(reservation);
    }

    /**
     * Retrieves all reservations.
     *
     * @return List of reservations.
     */
    public List<Reservation> listReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Cancels a reservation by ID.
     *
     * @param id Reservation ID.
     * @return Response indicating success.
     */
    public ResponseEntity<String> cancelReservation(Long id) {
        reservationRepository.deleteById(id);
        return ResponseEntity.ok("Reservation canceled");
    }

    /**
     * Checks availability of rooms within a date range.
     *
     * @param start Start date.
     * @param end   End date.
     * @return List of reservations.
     */
    public List<Reservation> checkAvailability(LocalDate start, LocalDate end) {
        return reservationRepository.findByCheckInDateBetween(start, end);
    }
}
