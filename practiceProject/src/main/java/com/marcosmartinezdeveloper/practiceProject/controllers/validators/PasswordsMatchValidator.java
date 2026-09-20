package com.marcosmartinezdeveloper.practiceProject.controllers.validators;

import com.marcosmartinezdeveloper.practiceProject.controllers.validators.projectAnnotations.PasswordsMatch;
import com.marcosmartinezdeveloper.practiceProject.facade.dtos.SignUpDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, SignUpDTO> {

	@Override
	public boolean isValid(SignUpDTO dto, ConstraintValidatorContext context) {

		if (dto == null) {
			return true;
		}

		if (dto.getPassword() != null && dto.getConfirmationPassword() != null
				&& !dto.getPassword().equals(dto.getConfirmationPassword())) {
			context.disableDefaultConstraintViolation();

			context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
					.addPropertyNode("confirmationPassword").addConstraintViolation();

			return false;
		}
		return true;
	}

}
