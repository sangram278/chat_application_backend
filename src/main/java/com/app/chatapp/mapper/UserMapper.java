package com.app.chatapp.mapper;

import com.app.chatapp.dto.UsersDTO;
import com.app.chatapp.entity.Users;

public class UserMapper {

	public static UsersDTO toDTO(Users user) {
        return new UsersDTO(
                user.getUserId(),
                user.getUsername(),
                user.getPhone(),
                user.getProfilePicture(),
                user.isOnlineStatus()
        );
    }

    public static Users toEntity(UsersDTO dto) {
        Users user = new Users();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setPhone(dto.getPhone());
        user.setProfilePicture(dto.getProfilePicture());
        user.setOnlineStatus(dto.isOnlineStatus());
        return user;
    }
}
