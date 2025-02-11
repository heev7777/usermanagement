package com.example.usermanagement.repositories;

import com.example.usermanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<User, Long> {
}

