package com.elmaghraby.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elmaghraby.app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
	User findUserByEmail(String email);
	User findUserByActivationHash(String activatationHash);
	User findUserById(Long id);
}
