package com.example.usermanagement.services;

import com.example.usermanagement.models.Notification;
import com.example.usermanagement.models.NotificationType;
import com.example.usermanagement.models.Reservation;
import com.example.usermanagement.models.ReservationStatus;
import com.example.usermanagement.models.Room;
import com.example.usermanagement.repositories.ReservationRepository;
import com.example.usermanagement.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final NotificationService notificationService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository, NotificationService notificationService) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.notificationService = notificationService;
    }

    public Reservation addReservation(Reservation reservation) {
        Room room = reservation.getRoom();
        if (!roomRepository.existsById(room.getRoomId())) {
            roomRepository.save(room);
        }
        Reservation savedReservation = reservationRepository.save(reservation);

        Notification notification = new Notification();
        notification.setType(NotificationType.RESERVATION_CONFIRMED);
        notification.setMessage("Reservation confirmed for room " + room.getRoomNumber());
        notification.setDate(LocalDateTime.now());
        notificationService.saveNotification(notification);

        return savedReservation;
    }

    public List<Reservation> listReservations() {
        return reservationRepository.findAll();
    }

    public ResponseEntity<String> cancelReservation(Long id) {
        reservationRepository.deleteById(id);

        Notification notification = new Notification();
        notification.setType(NotificationType.RESERVATION_CONFIRMED);
        notification.setMessage("Reservation with ID " + id + " has been canceled");
        notification.setDate(LocalDateTime.now());
        notificationService.saveNotification(notification);

        return ResponseEntity.ok("Reservation canceled");
    }

    public List<Reservation> checkAvailability(LocalDate start, LocalDate end) {
        return reservationRepository.findByCheckInDateBetween(start, end);
    }

    public boolean verifyReservation(Long id) {
        return reservationRepository.existsById(id);
    }

    public void updateReservationStatus(Long id, ReservationStatus status) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservation.setStatus(status);
        reservationRepository.save(reservation);

        Notification notification = new Notification();
        notification.setType(NotificationType.RESERVATION_CONFIRMED);
        notification.setMessage("Reservation status updated to " + status);
        notification.setDate(LocalDateTime.now());
        notificationService.saveNotification(notification);
    }
}