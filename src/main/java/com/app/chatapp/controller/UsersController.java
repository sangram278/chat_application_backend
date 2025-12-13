package com.app.chatapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatapp.entity.Users;
import com.app.chatapp.service.UsersService;

@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private UsersService userService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public Users login(@RequestBody Users user) {
        return userService.login(user);
    }

    @PutMapping("/status/{userId}")
    public Users updateStatus(@PathVariable Long userId, @RequestParam boolean online) {
        return userService.updateOnlineStatus(userId, online);
    }

    @PostMapping("/syncContacts/{userId}")
    public List<Users> syncContacts(@PathVariable Long userId, @RequestBody List<String> contacts) {
        return userService.getRegisteredContacts(userId, contacts);
    }

    @GetMapping("/{userId}")
    public Users getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
}
