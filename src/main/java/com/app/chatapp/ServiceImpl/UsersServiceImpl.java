package com.app.chatapp.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.chatapp.Repository.UsersRepository;
import com.app.chatapp.customException.UserNotFoundException;
import com.app.chatapp.entity.Users;
import com.app.chatapp.service.UsersService;
import com.app.chatapp.util.ActiveUserTracker;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private ActiveUserTracker activeUserTracker;

    @Override
    public Users registerUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Users> matchContacts(List<String> contacts) {
        return userRepository.findRegisteredContacts(contacts);
    }

    @Override
    public Users updateOnlineStatus(Long userId, boolean status) {
    	Users user = getUserById(userId);
        if(user!=null) user.setOnlineStatus(status);
		return userRepository.save(user);
    }
    
    @Override
    public boolean isUserOnline(Long userId) {

    	return activeUserTracker.isUserOnline(userId);
    }

	@Override
	public Users getUserById(Long userId) {
		Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
		return user;
	}

	@Override
	public Users login(String phone) {
	    return userRepository.findByPhone(phone)
	            .orElseThrow(() -> new UserNotFoundException("User not registered"));
	}


	@Override
	public List<Users> getRegisteredContacts(Long userId, List<String> contacts) {

	    // clean numbers such as "+91", space, "-"
	    List<String> cleanedContacts = contacts.stream()
	        .map(phone -> phone.replaceAll("[^0-9]", "")) // keep only digits
	        .toList();

	    // find registered users
	    List<Users> registeredUsers = userRepository.findRegisteredContacts(cleanedContacts);

	    // exclude self
	    return registeredUsers.stream()
	            .filter(u -> !u.getUserId().equals(userId))
	            .toList();
	}

	@Override
	public Users getUserByPhone(String phone) {
		return userRepository.findByPhone(phone)
	            .orElseThrow(() -> new UserNotFoundException("User with phone " + phone + " not registered"));
	}


}
