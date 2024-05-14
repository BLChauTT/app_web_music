package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Album;
import com.demo.services.AlbumService;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private Environment environment;
    @Autowired
    public AlbumController(AlbumService _albumService) {
    	this.albumService = _albumService;
    }

    @GetMapping("findAll")
    public String findAll(ModelMap modelMap) {
        String imageUrl = environment.getProperty("imageUrl");
        modelMap.put("imageUrl",imageUrl);
        modelMap.put("albums", albumService.findAll());
        return "user/album/albumFindAll2";
    }
    @GetMapping("details/{id}")
    public String details(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("album", albumService.find(id));
        return "user/album/albumDetail";
    }
    @GetMapping("add")
    public String add(ModelMap modelMap) {
        modelMap.put("album", new Album());
        return "album/add";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("album") Album category, RedirectAttributes redirectAttributes) {
        if (albumService.save(category)) {
            redirectAttributes.addFlashAttribute("msg", "Done!");
            return "redirect:/album/findAll";
        } else {
            redirectAttributes.addFlashAttribute("msg", "Failed");
            return "redirect:/album/add";
        }
    }

}
