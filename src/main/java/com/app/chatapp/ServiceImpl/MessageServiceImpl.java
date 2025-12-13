package com.app.chatapp.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.chatapp.Repository.MessageRepository;
import com.app.chatapp.customException.MessageNotFoundException;
import com.app.chatapp.entity.Message;
import com.app.chatapp.entity.Users;
import com.app.chatapp.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

    @Override
    public Message sendMessage(Message message) {
        message.setSeen(false);
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getConversation(Long userId, Long contactId) {
        return messageRepository.findChatBetweenUsers(userId, contactId);
    }

    @Override
    public List<Message> getUnseenMessages(Users receiver) {
        return messageRepository.findByReceiverAndSeenFalse(receiver);
    }

    @Override
    public Message markAsSeen(Long messageId) {
        Message msg = messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException("Message with id " + messageId + " not found"));

        msg.setSeen(true);
        return messageRepository.save(msg);
    }

}
