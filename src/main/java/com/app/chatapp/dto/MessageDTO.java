
package com.app.chatapp.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private Long messageId;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private String receiverName;
    @NotBlank
    private String content;
    private LocalDateTime timestamp;
    private boolean seen;
}
