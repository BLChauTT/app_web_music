package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entitiesjpa.Product;
import com.demo.services.ProductJPAService;

@Controller
@RequestMapping("demo12")
public class Demo12Controller {

	@Autowired
	private ProductJPAService productJPAService;

	@Autowired
	private Environment environment;

	@GetMapping({ "", "index", "/" })
	public String index(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, ModelMap modelMap) {
		int pageSize = Integer.parseInt(environment.getProperty("pageSize"));
		Page<Product> page = productJPAService.findAllPagination(pageNo, pageSize);
		modelMap.put("currentPage", pageNo);
		modelMap.put("totalPages", page.getTotalPages());
		modelMap.put("products", page.getContent());
		return "demo12/index";
	}

}
