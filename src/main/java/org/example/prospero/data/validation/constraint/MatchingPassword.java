package org.example.prospero.data.validation.constraint;

import org.example.prospero.data.validation.validator.MatchingPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = MatchingPasswordValidator.class)
public @interface MatchingPassword {

	String message() default "password and confirmPassword are not equal";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
