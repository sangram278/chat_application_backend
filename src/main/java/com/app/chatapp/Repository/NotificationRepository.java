package com.app.chatapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.chatapp.entity.Notification;
import com.app.chatapp.entity.Users;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	List<Notification> findByUserAndSeenFalse(Users user);
}
