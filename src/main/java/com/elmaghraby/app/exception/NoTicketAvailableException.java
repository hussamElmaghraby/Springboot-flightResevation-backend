package com.elmaghraby.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST , reason="no_ticket_available")
public class NoTicketAvailableException extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
