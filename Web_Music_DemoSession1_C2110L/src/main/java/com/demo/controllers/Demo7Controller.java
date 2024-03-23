package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.services.CountryService;
import com.demo.services.ProductService;

@Controller
@RequestMapping("demo7")
public class Demo7Controller {

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping({ "", "index", "/" })
	public String index() {
		return "demo7/index";
	}
	
	@GetMapping("index2")
	public String index2(ModelMap modelMap) {
		modelMap.put("countries", countryService.findAll());
		return "demo7/index2";
	}
	
	@GetMapping("index3")
	public String index3() {
		return "demo7/index3";
	}
	
	@GetMapping("index4")
	public String index4(ModelMap modelMap) {
		modelMap.put("products", productService.findAll());
		return "demo7/index4";
	}

}
