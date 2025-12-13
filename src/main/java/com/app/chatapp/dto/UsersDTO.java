package com.app.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
    private Long userId;
    private String username;
    private String phone;
    private String profilePicture;
    private boolean onlineStatus;
}
