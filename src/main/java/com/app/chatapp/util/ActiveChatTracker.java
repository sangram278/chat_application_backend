package com.app.chatapp.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class ActiveChatTracker {

	// userId -> chattingWithUserId
    private final Map<Long, Long> userCurrentChat = new ConcurrentHashMap<>();

    public void setUserViewingChat(Long userId, Long contactId) {
        userCurrentChat.put(userId, contactId);
    }

    public boolean isUserViewingChat(Long userId, Long chatWithUserId) {
        return chatWithUserId.equals(userCurrentChat.get(userId));
    }

    public void clearViewingChat(Long userId) {
        userCurrentChat.remove(userId);
    }
}
