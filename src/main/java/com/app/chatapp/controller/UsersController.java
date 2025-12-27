package com.app.chatapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.chatapp.customException.UserNotFoundException;
import com.app.chatapp.dto.LoginDTO;
import com.app.chatapp.entity.Users;
import com.app.chatapp.service.UsersService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService userService;

    // Register user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        try {
            Users newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Failed to register user: " + ex.getMessage());
        }
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        Users existing = userService.login(loginDTO.getPhone());
        return ResponseEntity.ok(existing);
    }

    // Update online/offline status
    @PutMapping("/status/{userId}")
    public ResponseEntity<?> updateStatus(@PathVariable Long userId, @RequestParam boolean online) {
        try {
            Users updated = userService.updateOnlineStatus(userId, online);
            return ResponseEntity.ok(updated);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    // Sync contacts
    @PostMapping("/syncContacts/{userId}")
    public ResponseEntity<?> syncContacts(@PathVariable Long userId, @RequestBody List<String> contacts) {
        try {
            List<Users> registeredContacts = userService.getRegisteredContacts(userId, contacts);
            return ResponseEntity.ok(registeredContacts);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    // Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        try {
            Users user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}
