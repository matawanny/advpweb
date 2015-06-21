package com.advp.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advp.spring.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

	User findByForgotPasswordCode(String forgotPasswordCode);

}
