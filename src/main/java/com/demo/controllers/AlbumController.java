package com.demo.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

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
import com.demo.entities.Album;
import com.demo.helpers.FileHelper;
import com.demo.services.AlbumService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("album")
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	@Autowired
	private Environment environment;

	@Autowired
	public AlbumController(AlbumService _albumService) {
		this.albumService = _albumService;
	}

	@GetMapping("findAll")
	public String findAll(ModelMap modelMap) {
		String imageUrl = environment.getProperty("imageUrl");
		modelMap.put("imageUrl", imageUrl);
		modelMap.put("albums", albumService.findAll());
		return "user/album/albumFindAll2";
	}

	@GetMapping("details/{id}")
	public String details(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("album", albumService.find(id));
		return "user/album/albumDetail";
	}

	@GetMapping("add")
	public String add(ModelMap modelMap) {
		modelMap.put("album", new Album());
		return "album/add";
	}

	@Autowired
	private AlbumService albumService;

	@Autowired
	public AlbumController(AlbumService _albumService) {
		this.albumService = _albumService;
	}

	@GetMapping("findAll")
	public String findAll(ModelMap modelMap) {
		modelMap.put("albums", albumService.findAll());
		return "user/album/albumFindAll2";
	}

	@GetMapping("details/{id}")
	public String details(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("album", albumService.find(id));
		return "user/album/albumDetail";
	}

	@GetMapping("add")
	public String add(ModelMap modelMap, HttpSession httpSession) {
		Account loggedInUser = (Account) httpSession.getAttribute("loggedInUser");
		if (loggedInUser == null) {
			return "redirect:/account/login";
		}
		modelMap.put("loggedInUser", loggedInUser);
		modelMap.put("album", new Album());
		return "user/album/add";
	}

	@PostMapping("add")
	public String add(@ModelAttribute("album") Album album, @RequestParam("fileImage") MultipartFile fileImage,
			HttpSession httpSession, RedirectAttributes redirectAttributes) {
		try {

			if (fileImage.isEmpty()) {
				album.setAlbumCoverUrl("no-image.jpg");
			} else {
				String fileName = FileHelper.generateFileName(fileImage.getOriginalFilename());
				File imagesFolder = new ClassPathResource("static/assets/images").getFile();
				Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
				System.out.println(path.toString());
				Files.copy(fileImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				album.setAlbumCoverUrl(fileName);

				Account loggedInUser = (Account) httpSession.getAttribute("loggedInUser");

				Date today = new Date();
				album.setReleaseDate(today);
				album.setAccount(loggedInUser);
			}
			if (albumService.save(album)) {
				redirectAttributes.addFlashAttribute("msg", "Success");
				return "redirect:/album/findAll";
			} else {
				redirectAttributes.addFlashAttribute("msg", "Failed");
				return "redirect:/album/add";
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
			return "redirect:/album/add";
		}
	}

}
