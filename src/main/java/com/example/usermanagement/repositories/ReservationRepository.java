package com.example.usermanagement.repositories;

import com.example.usermanagement.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCheckInDateBetween(LocalDate start, LocalDate end);
}