package com.demo.controllers;

import com.demo.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("song")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("index")
    public String song(ModelMap modelMap) {
        modelMap.put("song", songService.findAll());
        // test
        return "user/music";
    }
}
