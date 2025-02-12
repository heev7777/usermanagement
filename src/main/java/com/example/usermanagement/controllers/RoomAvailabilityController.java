package com.example.usermanagement.controllers;

import com.example.usermanagement.models.Room;
import com.example.usermanagement.services.RoomAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomAvailabilityController {

    private final RoomAvailabilityService roomAvailabilityService;

    @Autowired
    public RoomAvailabilityController(RoomAvailabilityService roomAvailabilityService) {
        this.roomAvailabilityService = roomAvailabilityService;
    }

    @GetMapping("/availability")
    public ResponseEntity<Boolean> checkAvailability(
            @RequestParam("roomId") String roomId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        boolean isAvailable = roomAvailabilityService.isRoomAvailable(roomId, startDate, endDate);
        return ResponseEntity.ok(isAvailable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room addRoom(@RequestBody Room room) {
        return roomAvailabilityService.addRoom(room.getRoomNumber(), room.getRoomType(), room.getPricePerNight());
    }

    @GetMapping
    public List<Room> getAllRooms(){
        return roomAvailabilityService.getAllRooms();
    }
    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable String roomId){
        return ResponseEntity.ok(roomAvailabilityService.getRoom(roomId));
    }
}