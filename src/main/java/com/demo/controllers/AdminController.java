package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.AccountSong;
import com.demo.entities.Comment;
import com.demo.entities.Rating;
import com.demo.services.AccountJPAService;
import com.demo.services.AccountSongService;
import com.demo.services.CommentService;
import com.demo.services.NotificationService;
import com.demo.services.RatingService;
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
	private SongDetailService songDetailJPAService;
	@Autowired
	private UserProfileService userProfileService;
	// music
	@Autowired
	private AccountSongService accountSongService;
	@Autowired
	private SongService songService;
	// rating
	@Autowired
	private RatingService ratingService;
	// comment
	@Autowired
	private CommentService commentService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private Environment environment;

	// có phân trang
	@GetMapping("index")
	public String admin(ModelMap modelMap, @RequestParam(name = "name", required = false) String name,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
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
	public String music(ModelMap modelMap, @RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		List<AccountSong> songs;

		if (keyword != null && !keyword.isEmpty()) {
			songs = accountSongService.findByTitle(keyword);
		} else {
			songs = accountSongService.findSongsWithPagination(pageNo, pageSize);
		}
		modelMap.put("accountSongs", songs);
		long totalItems = songDetailJPAService.countTotalSongs();
		int totalPages = (int) Math.ceil((double) totalItems / pageSize);
		modelMap.addAttribute("totalItems", totalItems);
		modelMap.addAttribute("totalPages", totalPages);
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("pageSize", pageSize);
		return "admin/musics/music";
	}

	@GetMapping("music/deletesong/{id}")
	public String deleteSongDetail(@PathVariable("id") int id, RedirectAttributes attr) {
		if (accountSongService.deleteAccountSongAndRelatedData(id)) {
			attr.addFlashAttribute("next", "Removed successfully.");
		} else {
			attr.addFlashAttribute("error", "Remove Failued.");
		}
		return "redirect:/admin/music";
	}

	@GetMapping(value = "remove/{id}")
	private String removeAccount(@PathVariable("id") int id, RedirectAttributes attr) {
		Account account = accountJPAService.findById(id);
		if (accountJPAService.remove(account)) {
			attr.addFlashAttribute("next", "Removed successfully.");
		} else {
			attr.addFlashAttribute("error", "Remove Failued.");
		}
		return "redirect:/admin/index";
	}


	@GetMapping("music/{accountId}")
	public String getMusicProfile(@PathVariable("accountId") int accountId, ModelMap modelMap) {
		modelMap.put("asongs", accountSongService.findByAccountId(accountId));
		modelMap.put("profile", userProfileService.findByAccountId(accountId));
		return "admin/musics/profileMusic";
	}

	@GetMapping("notification/{accountId}")
	public String getNotifications(@PathVariable("accountId") int accountId, ModelMap modelMap) {
		modelMap.put("notifications", notificationService.findByAccountId(accountId));
		modelMap.put("profile", userProfileService.findByAccountId(accountId));
		return "admin/evaluate/comment";
	}

	@GetMapping("/notification/delete/{id}")
	public String deleteComment(@PathVariable("id") int id, RedirectAttributes redirectAttributes,
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
		modelMap.put("ratings", ratingService.ratingByAccountId(accountId));
		modelMap.put("comments", commentService.commentByAccountId(accountId));
		modelMap.put("profile", userProfileService.findByAccountId(accountId));
		return "admin/evaluate/rating";
	}

	@GetMapping("ratingfindall")
	public String getRatingfindall(ModelMap modelMap,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		List<Rating> ratings;
		ratings = ratingService.findSongsWithPagination(pageNo, pageSize);
		modelMap.put("ratings", ratings);

		Long totalItems = ratingService.countTotalRatngs();
		int totalPages = (int) Math.ceil((double) totalItems / pageSize);
		modelMap.addAttribute("totalItems", totalItems);
		modelMap.addAttribute("totalPages", totalPages);
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("pageSize", pageSize);

		return "admin/evaluate/ratingFindAll";
	}

	@GetMapping("rating/delete/{id}")
	public String deleteRating(@PathVariable("id") int id, RedirectAttributes attr) {
		if (ratingService.delete(id)) {
			attr.addFlashAttribute("next", "Removed successfully.");
		} else {
			attr.addFlashAttribute("error", "Remove Failued.");
		}
		return "redirect:/admin/ratingfindall";
	}

	@GetMapping("commentfindall")
	public String getCommentfindall(ModelMap modelMap,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		List<Comment> comments;
		comments = commentService.findSongsWithPagination(pageNo, pageSize);

		modelMap.put("comments", comments);
		Long totalItems = commentService.countTotalComments();
		int totalPages = (int) Math.ceil((double) totalItems / pageSize);
		modelMap.addAttribute("totalItems", totalItems);
		modelMap.addAttribute("totalPages", totalPages);
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("pageSize", pageSize);
		return "admin/evaluate/commentFindAll";
	}

	@GetMapping("comment/delete/{id}")
	public String deleteComment(@PathVariable("id") int id, RedirectAttributes attr) {
		if (commentService.delete(id)) {
			attr.addFlashAttribute("next", "Removed successfully.");
		} else {
			attr.addFlashAttribute("error", "Remove Failued.");
		}
		return "redirect:/admin/commentfindall";
	}
}
