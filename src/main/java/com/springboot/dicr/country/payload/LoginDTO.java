package com.springboot.dicr.country.payload;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginDTO {

	@NotEmpty
	private String userNameOrEmail;
	
	@NotEmpty
	private String password;
}
