package com.app.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.app.chatapp.entity.Users;
import com.app.chatapp.util.ActiveUserTracker;

@Controller
public class UserStatusBroadcaster {

	@Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ActiveUserTracker activeUserTracker;

    public void broadcastStatus(Long userId, String username) {
        boolean online = activeUserTracker.isUserOnline(userId);

        Users user = new Users();
        user.setUserId(userId);
        user.setUsername(username);
        user.setOnlineStatus(online);

        messagingTemplate.convertAndSend("/topic/status", user); // send to all subscribers
    }
}
