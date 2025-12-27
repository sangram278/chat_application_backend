package com.app.chatapp.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatapp.dto.MessageDTO;
import com.app.chatapp.entity.Message;
import com.app.chatapp.entity.Users;
import com.app.chatapp.mapper.MessageMapper;
import com.app.chatapp.service.MessageService;
import com.app.chatapp.service.NotificationService;
import com.app.chatapp.service.UsersService;
import com.app.chatapp.util.ActiveChatTracker;

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
    
    @Autowired
    private ActiveChatTracker activeChatTracker;

    @MessageMapping("/chat.send/{receiverId}")
    public void sendMessage(@Payload @Valid MessageDTO messageDTO,
                            @DestinationVariable Long receiverId) {

        Users sender = userService.getUserById(messageDTO.getSenderId()); // get from payload
        Users receiver = userService.getUserById(receiverId);

        Message message = MessageMapper.toEntity(messageDTO, sender, receiver);
        boolean isOnline = userService.isUserOnline(receiverId);
        boolean isViewingChat =
                activeChatTracker.isUserViewingChat(receiverId, sender.getUserId());

        // ✅ correct seen logic
        message.setSeen(isOnline && isViewingChat);
        Message saved = messageService.sendMessage(message);
        MessageDTO dto = MessageMapper.toDTO(saved);

        messagingTemplate.convertAndSendToUser(
                receiverId.toString(),
                "/queue/messages",
                dto
        );

     // ✅ create notification ONLY if unseen
        if (!saved.isSeen()) {
            notificationService.createNotification(receiver, messageDTO.getContent());
        }
    }
    
    @PutMapping("/chat/open/{userId}/{contactId}")
    public void openChat(
            @PathVariable Long userId,
            @PathVariable Long contactId) {

        // track which chat user is viewing
        activeChatTracker.setUserViewingChat(userId, contactId);

        // mark messages as seen (contact -> user)
        messageService.markConversationAsSeen(contactId, userId);

        // clear notifications for this user
        Users user = userService.getUserById(userId);
        notificationService.markAsSeen(
                notificationService.getUnseenNotifications(user)
        );
    }
    
    @PutMapping("/chat/close/{userId}")
    public void closeChat(@PathVariable Long userId) {
        activeChatTracker.clearViewingChat(userId);
    }



}
