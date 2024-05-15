package com.demo.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.Role;
import com.demo.entities.Userprofile;
import com.demo.helpers.FileHelper;
import com.demo.helpers.RandomHelper;
import com.demo.services.AccountJPAService;
import com.demo.services.MailService;
import com.demo.services.UserProfileService;

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
//  + phân trang
//  + upload download nhạc

	@Autowired
	private AccountJPAService accountService;
	@Autowired
	private MailService mailService;
	@Autowired
	private Environment environment;
	@Autowired
    private UserProfileService userProfileService;

	@GetMapping({"login", ""})
	public String login() {
		return "account/login";
	}

	//login cũ
	@PostMapping("login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
	                    HttpSession session, RedirectAttributes redirectAttributes) {
		if (accountService.login(email, password)) {
	        Account loggedInUser = accountService.findByEmail(email);
	        if (loggedInUser != null) {
	            session.setAttribute("loggedInUser", loggedInUser);
	            System.out.println("Đăng nhập thành công. Email: " + email);

	            // Kiểm tra vai trò của người dùng và chuyển hướng
	            if (loggedInUser.getRole().getRoleId() == 1) {
	                return "redirect:/admin/index";
	            } else if (loggedInUser.getRole().getRoleId() == 2) {
	                return "redirect:/song/index";
	            }
	        } else {
	            System.out.println("Không tìm thấy người dùng với email này: " + email);
	            redirectAttributes.addFlashAttribute("mistake", "Invalid email or password");
	        }
	    } else {
	        System.out.println("Sai mật khẩu cho email: " + email);
	        redirectAttributes.addFlashAttribute("mistake", "Invalid email or password");
	    }
	    return "redirect:/account/login";
	}

	@GetMapping("welcome")
	public String welcome(HttpSession session, ModelMap modelMap) {
//		modelMap.put("username", session.getAttribute("email").toString());
//		return "account/welcome";
		Account loggedInUser = (Account) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			modelMap.put("loggedInUser", loggedInUser);
			return "account/welcome";
		} else {
			return "redirect:/account/login";
		}
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
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
			redirectAttributes.addFlashAttribute("next", "Mật khẩu đã được đổi thành công.");
		} else {
			redirectAttributes.addFlashAttribute("mistake", "Không thể đổi mật khẩu. Vui lòng thử lại sau.");
		}
		return "redirect:/account/login";
	}
	
	@GetMapping("edit/{accountId}")
    public String showEditForm(@PathVariable("accountId") int accountId, ModelMap model) {
        Optional<Userprofile> profileOpt = userProfileService.updateFindByAccountId(accountId);
        if (profileOpt.isPresent()) {
            model.addAttribute("userProfile", profileOpt.get());
            return "admin/accounts/editProfile";
        } else {
            // Handle the case where the profile is not found
            return "redirect:/error";
        }
    }

    @PostMapping("/update")
    public String updateUserProfile(@ModelAttribute("userProfile") Userprofile userProfile, @RequestParam("fileImage") MultipartFile fileImage) {
    	try {
            if (!fileImage.isEmpty()) {
                // Save the file locally
                String fileName = FileHelper.generateFileName(fileImage.getOriginalFilename());
                File imagesFolder = new ClassPathResource("static/assets/images").getFile();
                Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
                Files.createDirectories(path.getParent());
                Files.copy(fileImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                // Set the file path in the userProfile object
                userProfile.setAvatarUrl(fileName);
            } else {
                // Keep existing avatar URL if no new file is uploaded
                String existingAvatarUrl = userProfileService.getAvatarUrlByAccountId(userProfile.getAccount().getAccountId());
                userProfile.setAvatarUrl(existingAvatarUrl);
            }

            // Update the user profile
            userProfileService.updateProfile(userProfile.getAccount().getAccountId(), userProfile);

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/admin/profile/" + userProfile.getAccount().getAccountId();
    }
}
