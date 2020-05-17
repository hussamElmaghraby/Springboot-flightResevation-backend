package com.elmaghraby.app.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.elmaghraby.app.entities.Captcha;

public class UserDto {
	
	
	@NotEmpty
	@Email
	private String email;
	
	@Size(min=5 , max=50)
	private String password;
	
	@NotEmpty
	@Size(max=25)
	private String firstName;
	
	@NotEmpty
	@Size(max=25)
	private String lastName;
	
	@NotEmpty
    @Pattern(regexp = "\\d{9,11}")
	private String phoneNumber;
	
	
	private Captcha captcha;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Captcha getCaptcha() {
		return captcha;
	}

	public void setCaptcha(Captcha captcha) {
		this.captcha = captcha;
	}
}
