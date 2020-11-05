package com.arthurfp.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// <our_annotation, type_of_data_to_validate_against>
public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

	private String coursePrefix;

	// get value from field that is using the annotation
	@Override
	public void initialize(CourseCode theCourseCode) {
		coursePrefix = theCourseCode.value();
	}

	// params: html form data and helper class for additional error messages
	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
		boolean result;

		if (theCode != null) {
			result = theCode.startsWith(coursePrefix);
		} else {
			result = true;
		}

		return result;
	}

}
