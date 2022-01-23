package com.springboot.dicr.country.controller;

import org.springframework.http.ResponseEntity;

import com.springboot.dicr.country.payload.JwtAuthResponseDTO;
import com.springboot.dicr.country.payload.LoginDTO;
import com.springboot.dicr.country.payload.SignUpDTO;

public interface IAuthController {

	ResponseEntity<JwtAuthResponseDTO> authenticateUser(final LoginDTO loginDTO);

	ResponseEntity<?> registerUser(final SignUpDTO signUpDTO);
}
