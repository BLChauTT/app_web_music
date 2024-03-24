package com.demo.controllers;

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

import com.demo.entitiesjpa.Account;
import com.demo.helpers.RandomHelper;
import com.demo.services.AccountJPAService;
import com.demo.services.MailService;
import com.demo.services.RoleJPAService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("accountjpa")
public class AccountJPAController {

	@Autowired
	private RoleJPAService roleJPAService;

	@Autowired
	private AccountJPAService accountJPAService;

	@Autowired
	private Environment environment;

	@Autowired
	private MailService mailService;

	@GetMapping("login")
	public String login() {
		return "accountjpa/login";
	}

	@PostMapping("login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, RedirectAttributes redirectAttributes) {
		if (accountJPAService.login(username, password)) {
			session.setAttribute("username", username);
			return "redirect:/accountjpa/welcome";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed");
			return "redirect:/accountjpa/login";
		}
	}

	@GetMapping("welcome")
	public String welcome() {
		return "accountjpa/welcome";
	}

	@GetMapping("register")
	public String register(ModelMap modelMap) {
		Account account = new Account();
		modelMap.put("account", account);
		modelMap.put("roles", roleJPAService.findAll());
		return "accountjpa/register";
	}

	@PostMapping("register")
	public String register(@ModelAttribute("account") Account account, RedirectAttributes redirectAttributes) {
		account.setStatus(false);
		String securityCode = RandomHelper.random();
		account.setSecurityCode(securityCode);
		if (accountJPAService.save(account)) {

			/* Gui email kich hoat tai khoan */
			String content = "Nhan vao <a href='" + environment.getProperty("BASE_URL") + "accountjpa/verify?email="
					+ account.getEmail() + "&securitycode=" + securityCode + "'>day</a> de kich hoat";
			String from = environment.getProperty("spring.mail.username");
			mailService.send(from, account.getEmail(), "Verify", content);

			return "redirect:/accountjpa/login";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed");
			return "redirect:/accountjpa/register";
		}
	}

	@GetMapping("verify")
	public String verify(@RequestParam("email") String email, @RequestParam("securitycode") String securitycode,
			ModelMap modelMap) {
		Account account = accountJPAService.findByEmail(email);
		if (account != null) {
			if (account.getSecurityCode().equals(securitycode)) {
				account.setStatus(true);
				accountJPAService.save(account);
				modelMap.put("msg", "Kich hoat tai khoan thanh cong. Nhan vao <a href='" + environment.getProperty("BASE_URL") + "accountjpa/login'>day</a> de kich hoat");
			} else {
				modelMap.put("msg", "Khong the kich hoat tai khoan");
			}
		} else {
			modelMap.put("msg", "Email khong ton tai");
		}
		return "accountjpa/verify";
	}

}
