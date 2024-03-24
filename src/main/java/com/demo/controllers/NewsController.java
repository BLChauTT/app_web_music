package com.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("news")
public class NewsController {

	@GetMapping({ "", "index", "index", "/" })
	public String index() {
		return "news/index";
	}

}
