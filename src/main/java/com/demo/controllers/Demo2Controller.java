package com.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entities.Product;

@Controller
@RequestMapping("demo2")
public class Demo2Controller {

	// @RequestMapping(value = { "", "index", "/" }, method = RequestMethod.GET)
	@GetMapping({ "", "index", "/" })
	public String index(ModelMap modelMap) {
		Product product = new Product(1, "Name 1", "thumb1.gif", new Date(), 4.5, 2);
		modelMap.put("product", product);
		return "demo2/index";
	}
	
	@GetMapping("index2")
	public String index2(ModelMap modelMap) {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(1, "Name 1", "thumb1.gif", new Date(), 4.5, 2));
		products.add(new Product(2, "Name 2", "thumb2.gif", new Date(), 11, 3));
		products.add(new Product(3, "Name 3", "thumb3.gif", new Date(), 8.4, 8));
		modelMap.put("products", products);
		return "demo2/index2";
	}

}
