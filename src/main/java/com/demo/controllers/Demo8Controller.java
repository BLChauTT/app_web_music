package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.services.MailService;

@Controller
@RequestMapping("demo8")
public class Demo8Controller {

	@Autowired
	private MailService mailService;

	@GetMapping({ "", "index", "/" })
	public String index() {
		return "demo8/index";
	}

	@PostMapping("send")
	public String send(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("subject") String subject, @RequestParam("content") String content,
			RedirectAttributes redirectAttributes) {
		if (mailService.send(from, to, subject, content)) {
			redirectAttributes.addFlashAttribute("msg", "Sent");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed");
		}
		return "redirect:/demo8/index";
	}

}
