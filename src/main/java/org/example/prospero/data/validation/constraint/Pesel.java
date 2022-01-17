package org.example.prospero.data.validation.constraint;

import org.example.prospero.data.validation.validator.PeselValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Constraint(validatedBy = PeselValidator.class)
public @interface Pesel {

	String message() default "invalid pesel";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
