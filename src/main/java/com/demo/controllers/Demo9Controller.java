package com.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("demo9")
public class Demo9Controller {

	@GetMapping({ "", "index", "/" })
	public String index() {
		return "demo9/index";
	}

	@GetMapping("index2")
	public String index2() {
		return "demo9/index2";
	}
	
	@GetMapping("index3")
	public String index3() {
		return "demo9/index3";
	}

}
