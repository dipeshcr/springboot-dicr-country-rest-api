package com.springboot.dicr.country.exception;

import org.springframework.http.HttpStatus;

public class CountryAPIException extends RuntimeException{

	/**
	 * O Serial Version UID.
	 */
	private static final long serialVersionUID = -5109273317517854594L;
	
	private HttpStatus status;
	
	private String message;
	
	public CountryAPIException(HttpStatus status, String message) {
		this.status=status;
		this.message=message;
	}
	
	public CountryAPIException(HttpStatus status, String message, String message1) {
		super(message);
		this.status=status;
		this.message=message1;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
	
}
