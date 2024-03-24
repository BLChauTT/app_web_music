package com.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("demo6")
public class Demo6Controller {

	@GetMapping({ "", "index", "/" })
	public String index() {
		return "demo6/index";
	}

}
