package com.elmaghraby.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elmaghraby.app.dto.UserDto;
import com.elmaghraby.app.entities.User;
import com.elmaghraby.app.exception.InvalidCaptchaException;
import com.elmaghraby.app.service.CaptchaService;
import com.elmaghraby.app.service.UserService;

@RestController

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CaptchaService captchaService;
	
	@PostMapping(value="/users/register")
	public void register(@Valid @RequestBody UserDto userDto , HttpServletRequest request) {
		
		if(!captchaService.isCaptchaValid(userDto.getCaptcha())) {
			throw new InvalidCaptchaException();
		}
		 User user = userService.addUser(userDto);
//		 String baseUrl = request.getRequestURL().toString(); --> localhost:8080/api
		 String baseUrl = "http://localhost:4200";
		 userService.sendConfirmationEmailToUser(user , baseUrl);	 
	}
	
	@GetMapping("/users/confirm")
	public void activateEmail(@RequestParam("h") String activatationHash) {
		userService.activateEmail(activatationHash);
	}
	
	@GetMapping(value="/admin/users")
	public List<User> getAllUsers(){
		List<User> users = userService.getAllUsers();
		return users;
	}
	
	@PutMapping(value="/admin/users")
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user);
	}
	
	@DeleteMapping(value="/admin/users/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}
	
}
