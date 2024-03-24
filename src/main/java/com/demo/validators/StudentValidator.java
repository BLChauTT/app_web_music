package com.demo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.demo.entities.Student;

@Component
public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Student.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Student student = (Student) target;
		if (student.getUsername().equals("acc1")) {
			errors.rejectValue("username", "Exists");
		}
	}

}
