package com.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/invoice")
public class InvoiceController {

	@GetMapping({ "", "index", "/" })
	public String index() {
		return "admin/invoice/index";
	}

	@GetMapping("index2")
	public String index2() {
		return "admin/invoice/index2";
	}
	
	@GetMapping("index3")
	public String index3() {
		return "admin/invoice/index3";
	}

}
