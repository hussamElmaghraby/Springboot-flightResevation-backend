package com.elmaghraby.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elmaghraby.app.dto.CaptchaDto;
import com.elmaghraby.app.service.CaptchaService;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {
	
	@Autowired
	private CaptchaService captchaService;
	
	@GetMapping("")
	public CaptchaDto getNewCaptcha() {
		return this.captchaService.createCaptcha();
	} 
	
}
