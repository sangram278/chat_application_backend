package com.app.chatapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatapp.entity.Message;
import com.app.chatapp.service.MessageService;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/message/messages")
public class MessageController {

	@Autowired
    private MessageService messageService;

    @GetMapping("/{userId}/{contactId}")
    public List<Message> getConversation(@PathVariable Long userId, @PathVariable Long contactId) {
        return messageService.getConversation(userId, contactId);
    }

    @PutMapping("/seen/{messageId}")
    public Message markSeen(@PathVariable Long messageId) {
        return messageService.markAsSeen(messageId);
    }
}
