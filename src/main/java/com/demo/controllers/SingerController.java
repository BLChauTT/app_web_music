package com.demo.controllers;

import com.demo.services.SingerService;
import com.demo.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return "user/singerFindAll";
    }

    @GetMapping("details/{id}")
    public String details(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("sing", singerService.findSingerById(id));
        return "user/singerDetail";
    }

}
