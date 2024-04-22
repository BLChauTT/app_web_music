package com.demo.controllers;

import com.demo.entities.Account;
import com.demo.helpers.RandomHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.services.AccountJPAService;
import com.demo.services.MailService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("account")
public class AccountController {

	@Autowired
	private AccountJPAService accountService;
	@Autowired
	private MailService mailService;
	@Autowired
	private Environment environment;

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
			redirectAttributes.addFlashAttribute("mistake", "Invalid");
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
	
	@GetMapping("accessDenied")
	public String accessDenied() {
		return "account/accessDenied";
	}
	
	@GetMapping("signup")
	public String register(ModelMap modelMap) {
		Account account = new Account();
		modelMap.put("account", account);
		return "account/signup";
	}

	@PostMapping("register")
	public String register(@ModelAttribute("account") Account account, RedirectAttributes redirectAttributes) {
		account.setStatus(false);
		String securityCode = RandomHelper.random();
		account.setSecurityCode(securityCode);
		if (accountService.save(account)) {
			// gởi mail kích hoạt tài khoản
			String content = "Nhấn vào <a href='" + environment.getProperty("BASE_URL") + "account/verify?email="
					+ account.getEmail() + "&securityCode=" + account.getSecurityCode() + "'>đây để kích hoạt</a>";
			String from = environment.getProperty("spring.mail.username");
			mailService.send(from, account.getEmail(), "Kích hoạt tài khoản", content);
			return "redirect:/account/login";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed");
			return "redirect:/account/signup";
		}
	}

	@GetMapping("verify")
	public String verify(@RequestParam("email") String email, @RequestParam("securityCode") String securityCode, ModelMap modelMap) {
		Account account = accountService.findByEmail(email);
		if(account != null) {
			if(account.getSecurityCode().equals(securityCode)) {
				account.setStatus(true);
				accountService.save(account);
				modelMap.put("msg", "Success");
			}else {
				modelMap.put("msg", "K thể kích hoạt tài khoản");
			}
		}else {
			modelMap.put("msg", "Email k tồn tại");
		}
		return "account/verify";
	}
	
	@GetMapping("forgetPassword")
	public String forgetPassword() {
		return "account/forgetPassword";
	}
	
	@PostMapping("forgetPassword")
	public String forgetPassword(@ModelAttribute("account") Account account, RedirectAttributes redirectAttributes) {
		return "account/newPassword";
	}

}
