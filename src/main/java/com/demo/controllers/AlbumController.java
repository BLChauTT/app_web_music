package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.services.AlbumService;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    public AlbumController(AlbumService _albumService) {
    	this.albumService = _albumService;
    }

    @GetMapping("findAll")
    public String findAll(ModelMap modelMap) {
        modelMap.put("albums", albumService.findAll());
        return "user/album/albumFindAll";
    }
    @GetMapping("details/{id}")
    public String details(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("album", albumService.find(id));
        return "user/album/albumDetail";
    }

}
