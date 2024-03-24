package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entitiesjpa.Category;
import com.demo.services.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping({ "", "index", "/" })
	public String index(ModelMap modelMap) {
		modelMap.put("categories", categoryService.findAll());
		modelMap.put("count", categoryService.count());
		return "category/index";
	}

	@GetMapping("add")
	public String add(ModelMap modelMap) {
		modelMap.put("category", new Category());
		return "category/add";
	}

	@PostMapping("add")
	public String add(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
		if (categoryService.save(category)) {
			redirectAttributes.addFlashAttribute("msg", "Done!");
			return "redirect:/category/index";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed");
			return "redirect:/category/add";
		}

	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		if (categoryService.delete(id)) {
			redirectAttributes.addFlashAttribute("msg", "Done!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed");
		}
		return "redirect:/category/index";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("category", categoryService.find(id));
		return "category/edit";
	}

	@PostMapping("edit")
	public String edit(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
		if (categoryService.save(category)) {
			redirectAttributes.addFlashAttribute("msg", "Done!");
			return "redirect:/category/index";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed");
			return "redirect:/category/edit/" + category.getId();
		}
	}

}
