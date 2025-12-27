package com.app.chatapp.util;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.app.chatapp.controller.UserStatusBroadcaster;

@Component
public class WebSocketEventListener {
	@Autowired
	private ActiveUserTracker activeUserTracker;

	@Autowired
	private UserStatusBroadcaster broadcaster;

	@EventListener
	public void handleSessionConnected(SessionConnectedEvent event) {
	    Principal principal = event.getUser();
	    if (principal != null) {
	        Long userId = Long.valueOf(principal.getName());
	        activeUserTracker.userConnected(userId);
	        broadcaster.broadcastStatus(userId, principal.getName()); // notify all clients
	    }
	}

	@EventListener
	public void handleSessionDisconnected(SessionDisconnectEvent event) {
	    Principal principal = event.getUser();
	    if (principal != null) {
	        Long userId = Long.valueOf(principal.getName());
	        activeUserTracker.userDisconnected(userId);
	        broadcaster.broadcastStatus(userId, principal.getName()); // notify all clients
	    }
	}

}
