package com.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.Userprofile;
import com.demo.services.AccountJPAService;
import com.demo.services.UserProfileService;
@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private AccountJPAService accountJPAService;
	
	@Autowired
    private UserProfileService userProfileService;

	@GetMapping("index")
	public String admin(ModelMap modelMap) {
		modelMap.put("accounts", accountJPAService.findAll());
		// test
		return "admin/index";
	}

	@GetMapping("music")
	public String music() {
		return "admin/music";
	}

	@GetMapping(value = "remove/{id}")
	private String remove(@PathVariable int id, RedirectAttributes attr) {
		Account account = accountJPAService.findById(id);
		if(accountJPAService.remove(account)) {
			attr.addFlashAttribute("next", "Removed successfully.");
		} else {
			attr.addFlashAttribute("error", "Remove Failued.");
		}
		return "redirect:/admin/index";
	}
	
	@GetMapping("profile/{accountId}")
	public String getUserProfile(@PathVariable("accountId") int accountId, ModelMap modelMap) {
		modelMap.put("profiles", userProfileService.findByAccountId(accountId));
        return "admin/profile";
    }
}
