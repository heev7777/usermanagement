package com.example.usermanagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Represents a notification in the system
 */
@Entity
@Table(name = "notification")
public class Notification {

    // Table ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The notification message
    @NotBlank(message = "Message can not be blank")
    @Size(max = 500, message = "Size of the message should be less than 500 characters")
    @Column(name = "message")
    private String message;

    // The notification date
    @Column(name = "date")
    private LocalDateTime date;

    // The notification type
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Message can not be null")
    @Column(name = "type")
    private NotificationType type;

    public Notification() {}

    public Notification(String message, NotificationType type) {
        this.message = message;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

}