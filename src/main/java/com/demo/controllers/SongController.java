package com.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.demo.services.SongService;


@Controller
@RequestMapping("song")
public class SongController {


    @Autowired
    private SongService songService;

    @Autowired
    public SongController(SongService _songService) {
        this.songService = _songService;
    }

    @GetMapping("index")
    public String song(ModelMap modelMap) {
        modelMap.put("song", songService.findAll());
        // test
        return "user/music";
    }

    @GetMapping("findAll")
    public String findAll(ModelMap modelMap) {
        modelMap.put("songs", songService.findAll());
        return "user/musicFindAll";
    }
}
