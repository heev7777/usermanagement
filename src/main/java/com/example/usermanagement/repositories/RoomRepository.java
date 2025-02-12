package com.example.usermanagement.repositories;

import com.example.usermanagement.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {}

