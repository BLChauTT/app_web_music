package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.services.CategoryService;
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
    public String findAll(ModelMap modelMap) {
        modelMap.put("categories", categoryService.findAll());
        return "user/category/categoryFindAll";
    }
    @GetMapping("details/{id}")
    public String details(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("category", categoryService.find(id));
        return "user/category/categoryDetail";
    }
}
