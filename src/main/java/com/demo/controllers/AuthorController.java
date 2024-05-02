package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.services.AuthorService;

@Controller
@RequestMapping("author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService _authorService) {
    	this.authorService = _authorService;
    }

    @GetMapping("findAll")
    public String findAll(ModelMap modelMap) {
        modelMap.put("authors", authorService.findAll());
        return "user/authorFindAll";
    }
    @GetMapping("details/{id}")
    public String details(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("author", authorService.findAuthorById(id));
        return "user/author/authorDetail";
    }

}
