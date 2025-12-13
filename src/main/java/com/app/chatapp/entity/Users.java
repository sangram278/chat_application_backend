package com.app.chatapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    private String username;
    @Column(unique = true, nullable = false)
    private String phone;
    private String profilePicture;
    private boolean onlineStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMessages;
}

