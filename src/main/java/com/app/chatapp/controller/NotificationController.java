package com.app.chatapp.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatapp.entity.Notification;
import com.app.chatapp.entity.Users;
import com.app.chatapp.service.NotificationService;
import com.app.chatapp.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("http://localhost:5173/")
public class NotificationController {

	@Autowired
    private NotificationService notificationService;
	@Autowired
    private UsersService userService;

    // get unseen notifications
    @GetMapping("/notifications/{userId}")
    public List<Notification> getNotifications(@PathVariable Long userId) {
        Users user = userService.getUserById(userId);
        return notificationService.getUnseenNotifications(user);
    }
}