package com.elmaghraby.app.service;

import com.elmaghraby.app.dto.CaptchaDto;
import com.elmaghraby.app.entities.Captcha;

public interface CaptchaService {
	CaptchaDto createCaptcha();
	boolean isCaptchaValid(Captcha captcha);
}
