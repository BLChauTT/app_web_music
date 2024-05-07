package com.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.AccountSong;
import com.demo.entities.Rating;
import com.demo.entities.Songdetail;
import com.demo.services.AccountJPAService;
import com.demo.services.AccountSongService;
import com.demo.services.NotificationService;
import com.demo.services.SongDetailService;
import com.demo.services.UserProfileService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class AdminController {
	// account
	@Autowired
	private AccountJPAService accountJPAService;
	@Autowired
	private SongDetailService songDetailJPAService;
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

	//có phân trang
	@GetMapping("index")
	public String admin(ModelMap modelMap, @RequestParam(name = "name", required = false) String name,
						@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
						@RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize) {
		List<Account> listAccounts;
		long totalItems;

		if (name != null && !name.isEmpty()) {
			listAccounts = accountJPAService.findByusername(name);
			totalItems = listAccounts.size();
		} else {
			listAccounts = accountJPAService.findAccountsWithPagination(pageNo, pageSize);
			totalItems = accountJPAService.countTotalAccounts();
		}

		int totalPages = (int) Math.ceil((double) totalItems / pageSize);

		modelMap.put("listAccount", listAccounts);
		modelMap.addAttribute("totalItems", totalItems);
		modelMap.addAttribute("totalPages", totalPages);
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("pageSize", pageSize);

		return "admin/accounts/index";
	}

//	@GetMapping("music")
//	public String music(ModelMap modelMap, @RequestParam(name = "keyword", required = false) String keyword) {
//		modelMap.put("details", songDetailRepository.findAll());
//		return "admin/musics/music";
//	}
	
	@GetMapping("music")
	public String music(ModelMap modelMap, 
						@RequestParam(name = "keyword", required = false) String keyword, 
						@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
						@RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize) {
		List<Songdetail> songs;
		
		songs = songDetailJPAService.findSongsWithPagination(pageNo, pageSize);
		modelMap.put("details", songs);
		long totalItems = songDetailJPAService.countTotalSongs();
		int totalPages = (int) Math.ceil((double) totalItems / pageSize);
		modelMap.addAttribute("totalItems", totalItems);
		modelMap.addAttribute("totalPages", totalPages);
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("pageSize", pageSize);
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

	@GetMapping("rating/{accountId}")
	public String getRatingandComment(@PathVariable("accountId") int accountId, ModelMap modelMap) {
		modelMap.put("ratings", accountSongService.ratingByAccountId(accountId));
		modelMap.put("comments", accountSongService.commentByAccountId(accountId));
		modelMap.put("profile", userProfileService.findByAccountId(accountId));
		return "admin/evaluate/rating";
	}
}
