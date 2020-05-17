package com.elmaghraby.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elmaghraby.app.entities.Captcha;

@Repository
public interface CaptchaRepository extends JpaRepository<Captcha, Long> {
	
}
