package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.services.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("login")
	public String login() {
		return "account/login";
	}

	@PostMapping("login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, RedirectAttributes redirectAttributes) {
		if (accountService.login(username, password)) {
			session.setAttribute("username", username);
			return "redirect:/account/welcome";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Invalid");
			return "redirect:/account/login";
		}
	}

	@GetMapping("welcome")
	public String welcome() {
		return "account/welcome";
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		return "redirect:/account/login";
	}

}
