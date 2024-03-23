package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.services.CategoryService;
import com.demo.services.ProductJPAService;

@Controller
@RequestMapping("demo11")
public class Demo11Controller {

	@Autowired
	private ProductJPAService productJPAService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping({ "", "index", "/" })
	public String index() {
		return "demo11/index";
	}
	
	@GetMapping("index2")
	public String index2(ModelMap modelMap) {
		modelMap.put("products", productJPAService.findAllDTO());
		modelMap.put("categories", categoryService.findAllDTO());
		return "demo11/index2";
	}

}
