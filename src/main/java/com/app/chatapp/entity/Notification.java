package com.app.chatapp.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user; // The user who will receive this notification

    private String notificationMsg;
    private LocalDateTime timestamp;
    private boolean seen = false;
    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}

