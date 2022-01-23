package com.springboot.dicr.country.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.springboot.dicr.country.utils.BusinessMessages;

import lombok.Data;

@Data
public class SignUpDTO {

	@NotEmpty
	private String name;

	@NotEmpty
	private String userName;

	@NotEmpty
	private String email;

	@NotEmpty
	@Size(min = 6, message = BusinessMessages.USER_PASSWORD_SHOULD_HAVE_AT_LEAST_SIX_CHARACTERS)
	private String password;
}
