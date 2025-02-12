package com.example.usermanagement.repositories;

import com.example.usermanagement.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Reservation entity.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCheckInDateBetween(LocalDate start, LocalDate end);
    List<Reservation> findByRoomRoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
            String roomId, LocalDate endDate, LocalDate startDate);
}
