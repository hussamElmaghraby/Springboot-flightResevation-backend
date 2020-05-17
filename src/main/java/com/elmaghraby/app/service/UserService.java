package com.elmaghraby.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.elmaghraby.app.dto.UserDto;
import com.elmaghraby.app.entities.User;

public interface UserService extends UserDetailsService{
	User addUser(UserDto userDto);

	void sendConfirmationEmailToUser(User user , String baseUrl);

	void activateEmail(String activatationHash);

	List<User> getAllUsers();

	void updateUser(User user);

	void deleteUser(Long id);
	
	
}
