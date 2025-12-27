package com.app.chatapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

	 @NotBlank
	    private String phone;
}
