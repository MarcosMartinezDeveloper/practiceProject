package com.marcosmartinezdeveloper.practiceProject.controllers.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import com.marcosmartinezdeveloper.practiceProject.controllers.validators.projectAnnotations.SignUpValid;
import com.marcosmartinezdeveloper.practiceProject.facade.dtos.SignUpDTO;
import com.marcosmartinezdeveloper.practiceProject.persistence.entities.User;
import com.marcosmartinezdeveloper.practiceProject.persistence.repositories.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SignUpValidator implements ConstraintValidator<SignUpValid, SignUpDTO> {
	
	public UserRepository userRepository;

	@Override
	public boolean isValid(SignUpDTO dto, ConstraintValidatorContext context) {

		if (dto == null) {
			return true;
		}
		

		
		boolean isValid = true;

		isValid = this.arePasswordEquals(dto, context, isValid);
		isValid =  this.isUsernameRegistered(dto, context, isValid);
		isValid = this.isEmailRegistered(dto, context, isValid);
	
		if(!isValid) {
			context.disableDefaultConstraintViolation();
		}
		
		return isValid;
	}
	
	private boolean arePasswordEquals(SignUpDTO dto, ConstraintValidatorContext context, boolean isValid) {
		if (dto.getPassword() != null && dto.getConfirmationPassword() != null
				&& !dto.getPassword().equals(dto.getConfirmationPassword())) {

			context.buildConstraintViolationWithTemplate("Passwords were not equal")
					.addPropertyNode("confirmationPassword").addConstraintViolation();
			return false;
		}
		return isValid;
	}
	
	private boolean isUsernameRegistered(SignUpDTO dto, ConstraintValidatorContext context, boolean isValid) {
		if (StringUtils.hasLength(dto.getUsername()) && userRepository.findByUsername(dto.getUsername()).isPresent()) {
			context.buildConstraintViolationWithTemplate("The username is already registered")
					.addPropertyNode("username").addConstraintViolation();
			return false;
		}
		return isValid;
	}
	
	private boolean isEmailRegistered(SignUpDTO dto, ConstraintValidatorContext context, boolean isValid) {
		if (StringUtils.hasLength(dto.getEmail()) && userRepository.findByEmail(dto.getEmail()).isPresent()) {

			context.buildConstraintViolationWithTemplate("The email is already registered")
					.addPropertyNode("email").addConstraintViolation();
			return false;
		}
		return isValid;
	}
	
}
