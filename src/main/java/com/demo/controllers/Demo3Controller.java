package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.services.CalculateService;
import com.demo.services.DemoService;
import com.demo.services.ProductService;
import com.demo.services.RectangleService;

@Controller
@RequestMapping("demo3")
public class Demo3Controller {

	@Autowired
	private DemoService demoService;
	
	@Autowired
	private CalculateService calculateService;
	
	@Autowired
	private RectangleService rectangleService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping({ "", "index", "/" })
	public String index(ModelMap modelMap) {
		modelMap.put("msg1", demoService.hello());
		modelMap.put("msg2", demoService.hi("ABC"));
		modelMap.put("result1", calculateService.add(5.6, 2.4));
		modelMap.put("result2", calculateService.mul(5.6, 2.4));
		modelMap.put("area", rectangleService.area(2, 10));
		modelMap.put("perimeter", rectangleService.perimeter(2, 10));
		return "demo3/index";
	}
	
	@GetMapping("index2")
	public String index2(ModelMap modelMap) {
		modelMap.put("product", productService.find());
		return "demo3/index2";
	}
	
	@GetMapping("index3")
	public String index3() {
		return "demo3/index3";
	}

}
