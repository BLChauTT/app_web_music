package com.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entities.Address;
import com.demo.entities.Employee;
import com.demo.services.CertService;
import com.demo.services.LanguageService;
import com.demo.services.RoleService;

@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private CertService certService;

	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private RoleService roleService;

	@GetMapping("register")
	public String register(ModelMap modelMap) {
		modelMap.put("certs", certService.findAll());
		modelMap.put("languages", languageService.findAll());
		modelMap.put("roles", roleService.findAll());
		Employee employee = new Employee();
		employee.setUsername("acc1");
		employee.setDescription("ABC");
		employee.setDob(new Date());
		employee.setStatus(false);
		employee.setGender("m");
		employee.setCert(2);
		employee.setLanguages(new int[] { 1, 3, 5 });
		employee.setRole(3);
		employee.setId(123);
		employee.setAddress(new Address("st1", "w1"));
		modelMap.put("employee", employee);
		return "employee/register";
	}

	@PostMapping("register")
	public String register(@ModelAttribute("employee") Employee employee) {
		System.out.println("Employee Info");
		System.out.println("username: " + employee.getUsername());
		System.out.println("password: " + employee.getPassword());
		System.out.println("description: " + employee.getDescription());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("dob: " + simpleDateFormat.format(employee.getDob()));
		System.out.println("status: " + employee.isStatus());
		System.out.println("Languages");
		for (int languageId : employee.getLanguages()) {
			System.out.println(languageId);
		}
		System.out.println("role: " + employee.getRole());
		System.out.println("id: " + employee.getId());
		System.out.println("street: " + employee.getAddress().getStreet());
		System.out.println("ward: " + employee.getAddress().getWard());
		return "redirect:/employee/register";
	}

}
