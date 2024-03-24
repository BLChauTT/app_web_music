package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entities.Student;
import com.demo.validators.StudentValidator;

import jakarta.validation.Valid;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentValidator studentValidator;
	
	@GetMapping("register")
	public String register(ModelMap modelMap) {
		Student student = new Student();
		modelMap.put("student", student);
		return "student/register";
	}

	@PostMapping("register")
	public String register(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult) {
		studentValidator.validate(student, bindingResult);
		if (bindingResult.hasErrors()) {
			return "student/register";
		} else {
			return "student/success";
		}
	}

}
