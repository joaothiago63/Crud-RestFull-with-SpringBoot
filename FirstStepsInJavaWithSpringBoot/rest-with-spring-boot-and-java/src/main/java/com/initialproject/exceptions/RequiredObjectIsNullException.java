package com.initialproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException{
	
	public RequiredObjectIsNullException(String txt) {
		super(txt);
	}

	public RequiredObjectIsNullException() {
		super("It is not allowed to persist a null object!");
	}
	private static final long serialVersionUID = 1L;

}
