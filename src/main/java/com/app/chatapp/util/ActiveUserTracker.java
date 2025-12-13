package com.app.chatapp.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class ActiveUserTracker {

    // userId -> WebSocket session count
    private final Map<Long, Integer> activeUsers = new ConcurrentHashMap<>();

    public void userConnected(Long userId) {
        activeUsers.merge(userId, 1, Integer::sum); // increment connection count
    }

    public void userDisconnected(Long userId) {
        activeUsers.computeIfPresent(userId, (id, count) -> (count <= 1) ? null : count - 1);
    }

    public boolean isUserOnline(Long userId) {
        return activeUsers.containsKey(userId);
    }
}
