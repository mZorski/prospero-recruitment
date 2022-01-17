package org.example.prospero.data.validation.constraint;

import org.example.prospero.data.validation.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {

	String message() default "invalid phone number format";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
