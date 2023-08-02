package com.initialproject.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Date moment;
	private String message;
	private String details;
	
	public ExceptionResponse(Date moment, String message, String details) {
		this.moment = moment;
		this.message = message;
		this.details = details;
	}



	public Date getMoment() {
		return moment;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
}
