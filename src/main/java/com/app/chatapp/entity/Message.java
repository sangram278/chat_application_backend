package com.app.chatapp.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Users receiver;

    @Column(length = 1000)
    private String content;
    private LocalDateTime timestamp;
    private boolean seen = false;
    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}

