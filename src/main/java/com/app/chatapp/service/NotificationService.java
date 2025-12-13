package com.app.chatapp.service;

import java.util.List;

import com.app.chatapp.entity.Notification;
import com.app.chatapp.entity.Users;

public interface NotificationService {

	Notification createNotification(Users user, String message);
    List<Notification> getUnseenNotifications(Users user);
    void markAsSeen(List<Notification> notifications);
}
