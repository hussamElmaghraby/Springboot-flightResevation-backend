package com.elmaghraby.app.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Captcha {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private Long id;
	private String captcha;
	private Date createCaptcha;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public Date getCreateCaptcha() {
		return createCaptcha;
	}
	public void setCreateCaptcha(Date createCaptcha) {
		this.createCaptcha = createCaptcha;
	}
}
