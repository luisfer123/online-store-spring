package com.online.store.data.validate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { UniqueUsernameValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface UniqueUsername {

	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
}
