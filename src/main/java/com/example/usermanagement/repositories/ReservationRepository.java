package com.example.usermanagement.repositories;

import com.example.usermanagement.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoomRoomIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String roomId, LocalDate endDate, LocalDate startDate);
}
