package com.example.usermanagement.controllers;

import com.example.usermanagement.exceptions.NotificationNotFoundException;
import com.example.usermanagement.models.Notification;
import com.example.usermanagement.models.NotificationType;
import com.example.usermanagement.services.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller for managing notification HTTP requests
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * List all notifications
     * @return The list of notifications
     */
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    /**
     * Get a notification by its ID
     * @param id The ID of the notification
     * @return The found notification
     */
    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id) {
        Notification notification = notificationService.getNotificationById(id);
        if (notification == null) { // Throw 404 if notification is not found
            throw new NotificationNotFoundException("Notification not found");
        }
        return notification;
    }

    /**
     * Get a notification by its type
     * @param type The type of the notification
     * @return The found notification
     */
    @GetMapping("/type/{type}")
    public List<Notification> getNotificationByType(@PathVariable NotificationType type) {
        return notificationService.getAllNotificationsByType(type);
    }

    /**
     * Saves a notification in the system
     * @param notification The notification to be saved
     * @return The saved notification
     */
    @PostMapping
    public ResponseEntity<Notification> saveNotification(@Valid @RequestBody Notification notification) {
        notification.setDate(LocalDateTime.now()); // Set the date
        Notification notificationResponse = notificationService.saveNotification(notification);

        if (notificationResponse != null) {
            return ResponseEntity.ok(notificationResponse);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}