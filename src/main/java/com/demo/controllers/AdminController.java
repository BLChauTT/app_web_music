package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.services.AccountJPAService;
import com.demo.services.AccountSongService;
import com.demo.services.NotificationService;
import com.demo.services.SongDetailService;
import com.demo.services.SongService;
import com.demo.services.UserProfileService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class AdminController {
	// account
	@Autowired
	private AccountJPAService accountJPAService;
	@Autowired
	private UserProfileService userProfileService;
	// music
	@Autowired
	private AccountSongService accountSongService;
	@Autowired
	private SongDetailService songDetailRepository;
	// comment
	@Autowired
	private NotificationService notificationService;

	@GetMapping("index")
	public String admin(ModelMap modelMap) {
		modelMap.put("accounts", accountJPAService.findAll());
		// test
		return "admin/accounts/index";
	}

	@GetMapping("music")
	public String music(ModelMap modelMap) {
		modelMap.put("details", songDetailRepository.findAll());
		return "admin/musics/music";
	}

	@GetMapping(value = "remove/{id}")
	private String remove(@PathVariable int id, RedirectAttributes attr) {
		Account account = accountJPAService.findById(id);
		if (accountJPAService.remove(account)) {
			attr.addFlashAttribute("next", "Removed successfully.");
		} else {
			attr.addFlashAttribute("error", "Remove Failued.");
		}
		return "redirect:/admin/index";
	}

	@GetMapping("profile/{accountId}")
	public String getUserProfile(@PathVariable("accountId") int accountId, ModelMap modelMap) {
		modelMap.put("profile", userProfileService.findByAccountId(accountId));
		return "admin/accounts/profile";
	}

	@GetMapping("music/{accountId}")
	public String getMusicProfile(@PathVariable("accountId") int accountId, ModelMap modelMap) {
		modelMap.put("asongs", accountSongService.findByAccountId(accountId));
		modelMap.put("profile", userProfileService.findByAccountId(accountId));
		return "admin/musics/profileMusic";
	}

	@GetMapping("comment/{accountId}")
	public String getComment(@PathVariable("accountId") int accountId, ModelMap modelMap) {
		modelMap.put("notifications", notificationService.findByAccountId(accountId));
		modelMap.put("profile", userProfileService.findByAccountId(accountId));
		return "admin/evaluate/comment";
	}

	@GetMapping("/comment/delete/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		if (notificationService.delete(id)) {
			redirectAttributes.addFlashAttribute("msg", "Done!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed");
		}
		return "redirect:" + referer;
	}

}
