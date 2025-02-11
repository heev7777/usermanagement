package com.example.usermanagement.repositories;

import com.example.usermanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for accessing user data in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}

// findAll() -> retrive all records
// save(User user) -> insert or update a user
// findById(long id) -> Find a user by id