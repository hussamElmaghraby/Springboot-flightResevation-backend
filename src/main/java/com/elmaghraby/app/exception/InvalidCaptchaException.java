package com.elmaghraby.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST , reason="Invalid_Captcha")
public class InvalidCaptchaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
