package com.example.usermanagement.services;

import com.example.usermanagement.models.Notification;
import com.example.usermanagement.models.NotificationType;
import com.example.usermanagement.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing the business logic regarding notifications
 */
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    /**
     * Retrieves all notifications in the database
     * @return The notification list
     */
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    /**
     * Retrieves a notification by its ID if it's found in the database, or null otherwise
     * @param id The ID of the notification
     * @return The found notification, or null if it is not found
     */
    public Notification getNotificationById(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        return notification.orElse(null);
    }

    /**
     * Retrieves notifications by their type if found in the database, or null otherwise
     * @param type The type of the notifications
     * @return The notifications list, or null if they are not found
     */
    public List<Notification> getAllNotificationsByType(NotificationType type) {
        return notificationRepository.findAllNotificationsByType(type);
    }

    /**
     * Saves a notification in the database
     * @param notification The notification to be saved
     * @return The saved notification
     */
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}