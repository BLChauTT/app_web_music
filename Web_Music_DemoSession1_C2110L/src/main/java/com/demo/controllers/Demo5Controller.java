package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Language;
import com.demo.services.LanguageService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("demo5")
public class Demo5Controller {

	@Autowired
	private LanguageService languageService;

	@GetMapping({ "", "index", "/" })
	public String index(HttpSession session) {

		session.setAttribute("id", 123);
		session.setAttribute("username", "acc1");
		session.setAttribute("language", languageService.find());
		session.setAttribute("languages", languageService.findAll());

		// Huy Session
		session.removeAttribute("username");

		return "redirect:/demo5/index2";
	}

	@GetMapping("index2")
	public String index2(HttpSession session) {

		if (session.getAttribute("id") != null) {
			int id = Integer.parseInt(session.getAttribute("id").toString());
			System.out.println("id: " + id);
		}

		if (session.getAttribute("username") != null) {
			String username = session.getAttribute("username").toString();
			System.out.println("username: " + username);
		}

		if (session.getAttribute("language") != null) {
			Language language = (Language) session.getAttribute("language");
			System.out.println(language.toString());
		}

		if (session.getAttribute("languages") != null) {
			List<Language> languages = (List<Language>) session.getAttribute("languages");
			System.out.println("Language List");
			for (Language language : languages) {
				System.out.println(language.toString());
			}
		}

		return "demo5/index";
	}
	
	@GetMapping("index3")
	public String index3(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("msg", "Done!");
		return "redirect:/demo5/index4";
	}
	
	@GetMapping("index4")
	public String index4() {
		return "demo5/index2";
	}

}
