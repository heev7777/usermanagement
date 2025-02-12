package com.example.usermanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<User, Long> {
}

