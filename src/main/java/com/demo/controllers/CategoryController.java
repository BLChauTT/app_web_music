package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Category;
import com.demo.services.CategoryService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    public CategoryController (CategoryService _categoryService) {
        categoryService = _categoryService;
    }
    @GetMapping("findAll")
    public String findAll(ModelMap modelMap,
                          HttpSession httpSession,
                          @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                          @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize
                          ) {
        modelMap.put("categories", categoryService.findAll());
        return "user/category/categoryFindAll";
    }
    @GetMapping("details/{id}")
    public String details(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("category", categoryService.find(id));
        return "user/category/categoryDetail";
    }

    @GetMapping("add")
	public String add(ModelMap modelMap) {
		modelMap.put("category", new Category());
		return "user/category/add";
	}

	@PostMapping("add")
	public String add(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
		if (categoryService.save(category)) {
			redirectAttributes.addFlashAttribute("msg", "Done!");
			return "redirect:findAll";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed");
			return "redirect:add";
		}
	}
}
