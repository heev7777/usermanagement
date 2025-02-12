package com.example.usermanagement.services;

import com.example.usermanagement.exceptions.RoomNotFoundException;
import com.example.usermanagement.models.Reservation;
import com.example.usermanagement.models.Room;
import com.example.usermanagement.repositories.ReservationRepository;
import com.example.usermanagement.repositories.RoomRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomAvailabilityService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public RoomAvailabilityService(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional(readOnly = true)
    public boolean isRoomAvailable(String roomId, LocalDate startDate, LocalDate endDate) {
        List<Reservation> overlappingReservations =
                reservationRepository.findByRoomRoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
                        roomId, endDate, startDate);
        return overlappingReservations.isEmpty();
    }

    @Transactional
    public Room addRoom(String roomNumber, String roomType, double pricePerNight) {
        Room room = new Room(roomNumber, roomType, pricePerNight);
        return roomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Room getRoom(String roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found"));
    }
}