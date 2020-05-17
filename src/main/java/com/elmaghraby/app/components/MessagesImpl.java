package com.elmaghraby.app.components;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;


public class MessagesImpl implements Messages {
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public String getMessage(String code) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, null , locale);
	}

}
