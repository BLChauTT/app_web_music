package com.demo.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.demo.helpers.FileHelper;

@Controller
@RequestMapping("demo4")
public class Demo4Controller {

	@GetMapping({ "", "index", "/" })
	public String index() {
		return "demo4/index";
	}

	@GetMapping("index2")
	public String index2() {
		return "demo4/index2";
	}

	@PostMapping("upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		try {
			System.out.println("File Info");
			System.out.println("name: " + file.getOriginalFilename());
			System.out.println("size(byte): " + file.getSize());
			System.out.println("type: " + file.getContentType());
			// Upload File
			String fileName = FileHelper.generateFileName(file.getOriginalFilename());
			File imagesFolder = new ClassPathResource("static/assets/images").getFile();
			Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
			System.out.println(path.toString());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/demo4/index2";
	}

	@PostMapping("uploads")
	public String uploads(@RequestParam("files") MultipartFile[] files) {
		try {
			if (files != null && files.length > 0) {
				System.out.println("files: " + files.length);
				for (MultipartFile file : files) {
					System.out.println("name: " + file.getOriginalFilename());
					System.out.println("size(byte): " + file.getSize());
					System.out.println("type: " + file.getContentType());
					System.out.println("------------------");
					// Upload file
					String fileName = FileHelper.generateFileName(file.getOriginalFilename());
					File imagesFolder = new ClassPathResource("static/assets/images").getFile();
					Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/demo4/index2";
	}

	@GetMapping("searchByKeyword")
	public String searchByKeyword(@RequestParam("keyword") String keyword) {
		System.out.println("keyword: " + keyword);
		return "redirect:/demo4/index";
	}

	@GetMapping("searchByPrices")
	public String searchByPrices(@RequestParam("min") double min, @RequestParam("max") double max) {
		System.out.println("min: " + min);
		System.out.println("max: " + max);
		return "redirect:/demo4/index";
	}

	@PostMapping("login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		return "redirect:/demo4/index";
	}

	@PostMapping("update1")
	public String update1(@RequestParam("emails") String[] emails) {
		for (String email : emails) {
			System.out.println(email);
		}
		return "redirect:/demo4/index";
	}

	@PostMapping("update2")
	public String update2(@RequestParam("quantities") int[] quantities) {
		for (int quantity : quantities) {
			System.out.println(quantity);
		}
		return "redirect:/demo4/index";
	}

}
