package com.app.chatapp.mapper;

import com.app.chatapp.dto.NotificationDTO;
import com.app.chatapp.entity.Notification;
import com.app.chatapp.entity.Users;

public class NotificationMapper {

	public static NotificationDTO toDTO(Notification notification) {
        return new NotificationDTO(
                notification.getNotificationId(),
                notification.getUser().getUserId(),
                notification.getUser().getUsername(),
                notification.getNotificationMsg(),
                notification.getTimestamp(),
                notification.isSeen()
        );
    }

    public static Notification toEntity(NotificationDTO dto, Users user) {
        Notification notification = new Notification();
        notification.setNotificationId(dto.getNotificationId());
        notification.setUser(user);
        notification.setNotificationMsg(dto.getMessage());
        notification.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : null);
        notification.setSeen(dto.isSeen());
        return notification;
    }
}
