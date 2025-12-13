package com.app.chatapp.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.chatapp.Repository.NotificationRepository;
import com.app.chatapp.entity.Notification;
import com.app.chatapp.entity.Users;
import com.app.chatapp.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(Users user, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setNotificationMsg(message);
        notification.setSeen(false);
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getUnseenNotifications(Users user) {
        return notificationRepository.findByUserAndSeenFalse(user);
    }

    @Override
    public void markAsSeen(List<Notification> notifications) {
        notifications.forEach(n -> n.setSeen(true));
        notificationRepository.saveAll(notifications);
    }
}
