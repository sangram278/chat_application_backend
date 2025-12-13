package com.app.chatapp.service;

import java.util.List;

import com.app.chatapp.entity.Users;

public interface UsersService {

	Users registerUser(Users user);
    List<Users> getAllUsers();
    List<Users> matchContacts(List<String> contacts);
    Users updateOnlineStatus(Long userId, boolean status);
    boolean isUserOnline(Long userId);
	Users getUserById(Long userId);
	Users login(Users user);
	List<Users> getRegisteredContacts(Long userId, List<String> contacts);
	Users getUserByPhone(String name);
}
