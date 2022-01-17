package org.example.prospero.data.validation.validator;

import org.example.prospero.data.pesel.PeselParser;
import org.example.prospero.data.validation.constraint.Pesel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeselValidator implements ConstraintValidator<Pesel, String> {

	@Override
	public boolean isValid(String pesel, ConstraintValidatorContext context) {
		if (pesel == null) {
			return true;
		}

		return PeselParser.isValidPesel(pesel);
	}
}
