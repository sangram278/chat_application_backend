// package com.app.chatapp.dto;

package com.app.chatapp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private Long notificationId;
    private Long userId;
    private String username;
    private String message;
    private LocalDateTime timestamp;
    private boolean seen;
}
