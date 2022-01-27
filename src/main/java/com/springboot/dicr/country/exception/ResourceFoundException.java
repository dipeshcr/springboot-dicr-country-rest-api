package com.springboot.dicr.country.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceFoundException extends RuntimeException {

	/**
	 * O Serial Version UID.
	 */
	private static final long serialVersionUID = -2391301585209584987L;

	private String resourceName;

	private String fieldName;

	private String fieldValue;

	public ResourceFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s found with %s : '%s'", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

}
