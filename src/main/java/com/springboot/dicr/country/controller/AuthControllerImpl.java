package com.springboot.dicr.country.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dicr.country.model.RoleEntity;
import com.springboot.dicr.country.model.UserEntity;
import com.springboot.dicr.country.payload.JwtAuthResponseDTO;
import com.springboot.dicr.country.payload.LoginDTO;
import com.springboot.dicr.country.payload.SignUpDTO;
import com.springboot.dicr.country.repository.IRoleRepository;
import com.springboot.dicr.country.repository.IUserRepository;
import com.springboot.dicr.country.security.JwtTokenProvider;
import com.springboot.dicr.country.utils.BusinessMessages;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Auth Controller exposes sing-in and sign-up REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthControllerImpl implements IAuthController{
	
	@Autowired
	private AuthenticationManager authenticationAuthorizationManager;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passEncoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	@ApiOperation(value = "REST API to login or SignIn user to Country App")
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthResponseDTO> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
		Authentication authentication = this.authenticationAuthorizationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getUserNameOrEmail(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = tokenProvider.generateToken(authentication);
		
		return new ResponseEntity<>(new JwtAuthResponseDTO(token), HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "REST API to register or SignUp user to Country App")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDTO signUpDTO) {
		if (userRepository.existsByUserName(signUpDTO.getUserName())) {
			return new ResponseEntity<>(BusinessMessages.USERNAME_IS_ALREADY_TAKEN + signUpDTO.getUserName(), HttpStatus.BAD_REQUEST);
		}
		
		if (userRepository.existsByEmail(signUpDTO.getEmail())) {
			return new ResponseEntity<>(BusinessMessages.EMAIL_IS_ALREADY_TAKEN + signUpDTO.getUserName(), HttpStatus.BAD_REQUEST);
		}
		
		UserEntity user = new UserEntity();
		user.setName(signUpDTO.getName());
		user.setUserName(signUpDTO.getUserName());
		user.setEmail(signUpDTO.getEmail());
		user.setPassword(this.passEncoder.encode(signUpDTO.getPassword()));
		
		RoleEntity role = this.roleRepository.findByName("ROLE_ADMIN").get();
		
		user.setRoles(Collections.singleton(role));
		
		this.userRepository.save(user);

		return new ResponseEntity<>("User registered sucessfully",HttpStatus.OK);
	}

}
