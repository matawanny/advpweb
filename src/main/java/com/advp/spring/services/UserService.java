package com.advp.spring.services;

import org.springframework.validation.BindingResult;

import com.advp.spring.dto.ForgotPasswordForm;
import com.advp.spring.dto.ResetPasswordForm;
import com.advp.spring.dto.SignupForm;
import com.advp.spring.dto.UserEditForm;
import com.advp.spring.entities.User;

public interface UserService {
	
	public abstract void signup(SignupForm signupForm);

	public abstract void verify(String verificationCode);

	public abstract void forgotPassword(ForgotPasswordForm forgotPasswordForm);

	public abstract void resetPassword(String forgotPasswordCode,
			ResetPasswordForm resetPasswordForm, BindingResult result);

	public abstract User findOne(long userId);

	public abstract void update(long userId, UserEditForm userEditForm);

}
