package com.app.chatapp.service;

import java.util.List;

import com.app.chatapp.entity.Message;
import com.app.chatapp.entity.Users;

public interface MessageService {

	Message sendMessage(Message message);
    List<Message> getUnseenMessages(Users receiver);
    List<Message> getConversation(Long userId, Long contactId);

    Message markAsSeen(Long messageId);
}
