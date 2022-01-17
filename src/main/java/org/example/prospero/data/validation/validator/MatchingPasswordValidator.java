package org.example.prospero.data.validation.validator;

import org.example.prospero.data.validation.constraint.MatchingPassword;
import org.example.prospero.data.dto.RegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPasswordValidator implements ConstraintValidator<MatchingPassword, RegistrationDto> {

	@Override
	public boolean isValid(RegistrationDto value, ConstraintValidatorContext context) {
		return value.getPassword() != null && value.getPassword().equals(value.getConfirmPassword());
	}
}
