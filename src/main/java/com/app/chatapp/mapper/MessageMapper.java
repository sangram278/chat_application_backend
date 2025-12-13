package com.app.chatapp.mapper;

import com.app.chatapp.dto.MessageDTO;
import com.app.chatapp.entity.Message;
import com.app.chatapp.entity.Users;

public class MessageMapper {
	
	public static MessageDTO toDTO(Message message) {
        return new MessageDTO(
                message.getMessageId(),
                message.getSender().getUserId(),
                message.getSender().getUsername(),
                message.getReceiver().getUserId(),
                message.getReceiver().getUsername(),
                message.getContent(),
                message.getTimestamp(),
                message.isSeen()
        );
    }

    public static Message toEntity(MessageDTO dto, Users sender, Users receiver) {
        Message message = new Message();
        message.setMessageId(dto.getMessageId());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(dto.getContent());
        message.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : null); // will use @PrePersist
        message.setSeen(dto.isSeen());
        return message;
    }

}
