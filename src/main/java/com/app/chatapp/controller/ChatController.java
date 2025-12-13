package com.app.chatapp.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatapp.dto.MessageDTO;
import com.app.chatapp.entity.Message;
import com.app.chatapp.entity.Users;
import com.app.chatapp.mapper.MessageMapper;
import com.app.chatapp.service.MessageService;
import com.app.chatapp.service.NotificationService;
import com.app.chatapp.service.UsersService;

import jakarta.validation.Valid;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UsersService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send/{receiverId}")
    public void sendMessage(
            @Payload @Valid MessageDTO messageDTO,
            @DestinationVariable Long receiverId,
            Principal principal) {
    	

        Users sender = userService.getUserByPhone(principal.getName());
        Users receiver = userService.getUserById(receiverId);

        Message message = MessageMapper.toEntity(messageDTO, sender, receiver);
        
        Message saved = messageService.sendMessage(message);
        
        MessageDTO dto = MessageMapper.toDTO(saved);

        // send to receiver
        messagingTemplate.convertAndSendToUser(
                receiverId.toString(),
                "/queue/messages",
                dto
        );

        // notify if offline
        if (!userService.isUserOnline(receiverId)) {
            notificationService.createNotification(receiver, messageDTO.getContent());
        }
    }
}
