package com.elmaghraby.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elmaghraby.app.components.Messages;
import com.elmaghraby.app.dao.UserRepository;
import com.elmaghraby.app.dto.UserDto;
import com.elmaghraby.app.entities.Role;
import com.elmaghraby.app.entities.User;
import com.elmaghraby.app.exception.EmailNotConfirmedException;
import com.elmaghraby.app.exception.EmailNotValidException;
import com.elmaghraby.app.exception.UserAlreadyExistsException;
import com.elmaghraby.app.service.RoleService;
import com.elmaghraby.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private Messages messages;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public User addUser(UserDto userDto) {
		User foundUser = userRepository.findUserByEmail(userDto.getEmail());
		
		if(foundUser != null) {
			throw new UserAlreadyExistsException();
		}
		ModelMapper modelMapper = new ModelMapper();
		
		User addedUser  = modelMapper.map(userDto, User.class);
		addedUser.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
		addedUser.setActivated(false);
		addedUser.setActivationHash(UUID.randomUUID().toString());
		
		List<Role> roles = new ArrayList<>();
		roles.add(roleService.createOrGetRoleUser());
		addedUser.setRoles(roles);
		User savedUser = userRepository.save(addedUser);
		return savedUser;
	}
	
	@Override
	public void activateEmail(String activatationHash) {
		User user = userRepository.findUserByActivationHash(activatationHash);
		
		if(user == null ) {
			throw new EmailNotConfirmedException();
		}
		user.setActivated(true);
		userRepository.save(user);
	}

	@Override
	public void sendConfirmationEmailToUser(User user, String baseUrl) {
		
		MimeMessage message = mailSender.createMimeMessage();
//		String url = baseUrl+"/users/activate-account?h="+user.getActivationHash();
//		String messageText = messages.getMessage("confirm_email.thank_user");
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message , false , "utf-8");
			helper.setTo(user.getEmail());
			helper.setFrom(new InternetAddress("hossam10elmaghraby@gmail.com"));
			helper.setSubject(messages.getMessage("confirm_email.subject"));
			helper.setText( "<a href=\"" + baseUrl + "/activate-account?h=" + user.getActivationHash() + "\">" +
                    messages.getMessage("confirm_email.link_message") +
                    "</a>",
            true);
			mailSender.send(message);
			
		}catch(MailException | MessagingException ex) {
			log.warn("Email could not be sent to user '{}'",user.getEmail(), ex);
//			throw new EmailNotValidException();
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void updateUser(User user) {
	User foundUser = userRepository.findUserById(user.getId());
	if(foundUser == null) {
		throw new UserAlreadyExistsException();
	}
		user.setPassword(foundUser.getPassword());
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User foundUser = userRepository.findUserByEmail(username);
		
		if(foundUser == null) {
			throw new UsernameNotFoundException("USER_NOT_FOUND");
		}
		 //if they presented the correct username and password and the user is enabled
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(Role role : foundUser.getRoles()) {
			// returns => true if this Set did not already contain the specified element
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return new org.springframework.security.core.userdetails.User(username, foundUser.getPassword()
							, true, true, true , foundUser.isActivated(), grantedAuthorities);
	}
	
	

	
}
