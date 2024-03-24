package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.services.ProductJPAService;

@Controller
@RequestMapping("productlanguage")
public class ProductLanguageController {

	@Autowired
	private ProductJPAService productJPAService;

	@GetMapping({ "", "index", "/" })
	public String index(ModelMap modelMap) {
		modelMap.put("products", productJPAService.findAll());
		return "productlanguage/index";
	}


}
