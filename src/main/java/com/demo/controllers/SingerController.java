package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.services.SingerService;

@Controller
@RequestMapping("singer")
public class SingerController {
    @Autowired
    private SingerService singerService;
    @Autowired
    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @GetMapping("findAll")
    public String findAll(ModelMap modelMap) {
        modelMap.put("singers", singerService.findAll());
        return "user/singer/singerFindAll";
    }

    @GetMapping("details/{id}")
    public String details(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("singer", singerService.findSingerById(id));
        return "user/singer/singerDetail";
    }

}
