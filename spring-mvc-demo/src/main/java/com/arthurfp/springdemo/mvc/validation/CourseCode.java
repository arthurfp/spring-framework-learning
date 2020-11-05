package com.arthurfp.springdemo.mvc.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// class used to validate the custom annotation
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
// where can be used(e.g., method, field)
@Target({ ElementType.METHOD, ElementType.FIELD })
// when to process (e.g., runtime) -- how long to preserve --- "retained till"
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

	// define default course code
	public String value() default "LUV";

	// define default error message
	public String message() default "must start with LUV";

	// define default groups (Groups: can group related constraints)
	public Class<?>[] groups() default {};

	// define default payloads (Payloads: provide custom details about validation
	// failure (severity level, error code etc))
	public Class<? extends Payload>[] payload() default {};

}
