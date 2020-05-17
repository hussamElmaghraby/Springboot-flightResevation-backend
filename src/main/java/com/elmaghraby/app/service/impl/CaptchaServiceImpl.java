package com.elmaghraby.app.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elmaghraby.app.dao.CaptchaRepository;
import com.elmaghraby.app.dto.CaptchaDto;
import com.elmaghraby.app.entities.Captcha;
import com.elmaghraby.app.service.CaptchaService;

@Service
@Transactional
public class CaptchaServiceImpl implements CaptchaService {

	@Autowired
	private CaptchaRepository captchaRepository;

	@Override
	public CaptchaDto createCaptcha() {
		String captchaText = getRandomText(6);
		byte[] captchaImageBytes = getCatchaImageBytes(captchaText);
		
		Captcha captcha =  new Captcha();
		captcha.setCaptcha(captchaText);
		captcha.setCreateCaptcha(new Date());
		
		Captcha addedCaptcha = captchaRepository.save(captcha);
		
		CaptchaDto captchaDto = new CaptchaDto();
		
		captchaDto.setId(addedCaptcha.getId());
		
		captchaDto.setCaptchaImage(captchaImageBytes);
		
		return captchaDto;
	}

	@Override
	public boolean isCaptchaValid(Captcha captcha) {
		
		Captcha foundCaptcha = captchaRepository.getOne(captcha.getId());
		
		// compare if null and Captcha text 
		boolean captchaValid = foundCaptcha != null && foundCaptcha.getCaptcha().equals(captcha.getCaptcha());
		
		if(!captchaValid) {
			captchaRepository.delete(captcha);
		}
		
		return captchaValid;
	}
	
	//create random method .. 
	private String getRandomText(int captchaLength) {
		String saltCars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		// provide much of functionality of string ..
		StringBuffer captchaStrBuffer = new StringBuffer();
		Random rnd = new Random();

		while (captchaStrBuffer.length() < captchaLength) {
			// get the index from the nextFloatValue * saltCarsLength
			int index = (int) (rnd.nextFloat() * saltCars.length());
			captchaStrBuffer.append(saltCars.substring(index, index + 1));
		}
		return captchaStrBuffer.toString();
	}

	private byte[] getCatchaImageBytes(String captchaText) {
		byte[] imageBytes = null;
		
		try {
			int width = 100;
			int height = 40;
			
			// rgb
			Color bg = new Color(0 , 255 ,255);
			Color fg = new Color(0 ,100 , 0);
			
			Font font  = new Font("Arial" , Font.BOLD , 20);
			
			BufferedImage cpImg = new BufferedImage(width , height , BufferedImage.OPAQUE);
			Graphics graphics = cpImg.createGraphics();
			
			graphics.setFont(font);
			graphics.setColor(bg);
			graphics.fillRect(0, 0, width, height);
			graphics.setColor(fg);
			graphics.drawString(captchaText, 10, 25);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(cpImg, "png", baos);
			 imageBytes = baos.toByteArray();
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
 		return imageBytes;
	}

}
