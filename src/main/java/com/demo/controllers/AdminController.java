package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.services.AccountJPAService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AccountJPAService accountJPAService;

	@GetMapping("index")
	public String admin(ModelMap modelMap) {
		modelMap.put("accounts", accountJPAService.findAll());
		return "admin/index";
	}

	@GetMapping("music")
	public String music() {
		return "admin/music";
	}

}
