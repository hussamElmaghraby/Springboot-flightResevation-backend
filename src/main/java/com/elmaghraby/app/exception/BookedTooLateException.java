package com.elmaghraby.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST , reason="booked_too_late")
public class BookedTooLateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
