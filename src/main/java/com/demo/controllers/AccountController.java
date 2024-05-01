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

import com.demo.entities.Account;
import com.demo.entities.Role;
import com.demo.entities.Userprofile;
import com.demo.helpers.RandomHelper;
import com.demo.services.AccountJPAService;
import com.demo.services.MailService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("account")
public class AccountController {
	//các chức năng để nói khi báo cáo:
//	+ login
//	+ register bằng mail
//	+ forget Password bằng mail
//	+ bảo bật bằng token, ngăn ngừa user truy cập link trái phép
//	+ bảo mật bằng recaptcha của gg đế tránh bị robot tấn công (spam)

	@Autowired
	private AccountJPAService accountService;
	@Autowired
	private MailService mailService;
	@Autowired
	private Environment environment;

	@GetMapping({"login", ""})
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
		Userprofile userprofile = new Userprofile();
		modelMap.put("userprofile", userprofile);
		modelMap.put("account", account);
		return "account/signup";
	}

	@PostMapping("register")
	public String register(@ModelAttribute("account") Account account, @ModelAttribute("userprofile") Userprofile userprofile, RedirectAttributes redirectAttributes) {
		Role role = new Role();
		role.setRoleId(2);
		role.setRoleName("User");

		account.setStatus(false);
		account.setRole(role);
		String securityCode = RandomHelper.random();
		account.setSecurityCode(securityCode);
		if (accountService.save(account)) {
			Account account2 = accountService.findByEmail(account.getEmail());
			userprofile.setAccount(account2);
			userprofile.setAvatarUrl("noimage.jpg");
			accountService.saveUserProfile(userprofile);

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
	public String verify(@RequestParam("email") String email, @RequestParam("securityCode") String securityCode,
			ModelMap modelMap) {
		Account account = accountService.findByEmail(email);
		if (account != null) {
			if (account.getSecurityCode().equals(securityCode)) {
				account.setStatus(true);
				accountService.save(account);
				modelMap.put("msg", "Success");
			} else {
				modelMap.put("msg", "K thể kích hoạt tài khoản");
			}
		} else {
			modelMap.put("msg", "Email k tồn tại");
		}
		return "account/verify";
	}

	@GetMapping("forgetPassword")
	public String forgetPassword(ModelMap modelMap) {
		Account account = new Account();
		modelMap.put("account", account);
		return "account/forgetPassword";
	}

	@PostMapping("forgetPassword")
	public String forgetPassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
		Account account = accountService.findByEmail(email);
		if (account != null) {
			String newPassword = RandomHelper.random();
			String newToken = RandomHelper.random();
			//account.setPassword(newPassword);
			account.setToken(newToken);
			accountService.save(account);

			String newPasswordLink = environment.getProperty("BASE_URL") + "account/newPassword?email=" + email + "&token=" + newToken;

			String emailContent = "Click vào <a href='" + newPasswordLink
					+ "'>đây</a> để đổi mật khẩu mới.";
			String emailFrom = environment.getProperty("spring.mail.username");
			mailService.send(emailFrom, email, "Đổi mật khẩu mới", emailContent);

			redirectAttributes.addFlashAttribute("msg", "Mật khẩu mới đã được gửi đến email của bạn.");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Email không tồn tại trong hệ thống.");
		}
		return "redirect:/account/forgetPassword";
	}

	@GetMapping("newPassword")
	public String newPassword(@RequestParam("email") String email, @RequestParam("token") String token, ModelMap modelMap) {
		if (isValidToken(email, token)) {
	        Account account = new Account();
	        account.setEmail(email);
	        modelMap.put("account", account);
	        return "account/newPassword";
	    } else {
	    	return "redirect:/account/accessDenied";
	    }
	}

	private boolean isValidToken(String email, String token) {
		Account account = accountService.findByEmailAndToken(email, token);
	    if (account != null) {
	        return true; // Token hợp lệ
	    } else {
	        return false; // Token không hợp lệ
	    }
	}

	@PostMapping("newPassword")
	public String newPassword(@RequestParam("email") String email, @RequestParam("password") String password,
			RedirectAttributes redirectAttributes) {
		Account account = accountService.findByEmail(email);
		if (account != null) {
			account.setPassword(password);
			accountService.save(account);
			redirectAttributes.addFlashAttribute("mistake", "Mật khẩu đã được đổi thành công.");
		} else {
			redirectAttributes.addFlashAttribute("mistake", "Không thể đổi mật khẩu. Vui lòng thử lại sau.");
		}
		return "redirect:/account/login";
	}

}
