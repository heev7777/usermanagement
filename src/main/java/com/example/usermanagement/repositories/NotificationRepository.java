package com.example.usermanagement.repositories;

import com.example.usermanagement.models.Notification;
import com.example.usermanagement.models.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Notification repository that interacts with the database
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllNotificationsByType(NotificationType type);

}