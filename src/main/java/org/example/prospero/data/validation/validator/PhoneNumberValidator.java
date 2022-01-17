package org.example.prospero.data.validation.validator;

import org.example.prospero.data.validation.constraint.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || value.matches("\\d{3}[- ]?\\d{3}[- ]?\\d{3}");
	}
}
