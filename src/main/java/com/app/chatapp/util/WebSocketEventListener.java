package com.app.chatapp.util;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    @Autowired
    private ActiveUserTracker activeUserTracker;

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        Principal principal = event.getUser();
        if (principal != null) {
            Long userId = Long.valueOf(principal.getName()); // or map phone -> id
            activeUserTracker.userConnected(userId);
        }
    }

    @EventListener
    public void handleSessionDisconnected(SessionDisconnectEvent event) {
        Principal principal = event.getUser();
        if (principal != null) {
            Long userId = Long.valueOf(principal.getName());
            activeUserTracker.userDisconnected(userId);
        }
    }
}
