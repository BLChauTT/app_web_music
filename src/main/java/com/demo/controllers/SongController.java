package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entities.Author;
import com.demo.entities.Song;
import com.demo.services.AuthorService;
import com.demo.services.SongService;

@Controller
@RequestMapping("song")
public class SongController {
    @Autowired
    private SongService songService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    public SongController(SongService _songService) {
        this.songService = _songService;
    }

    @GetMapping({"index", "","/"})
    public String song(ModelMap modelMap) {
        //modelMap.put("song", songService.findAll());
        return "user/musicTest/main.music";
    }
    @GetMapping("findAll")
    public String findAll(ModelMap modelMap) {
        modelMap.put("songs", songService.findAll());
        return "user/musicTest/musicFindAll";
    }
    @GetMapping("details/{id}")
    public String details(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("song", songService.findSongById(id));
        return "user/musicTest/musicDetail";
    }
    @GetMapping("/songs/by-author/{authorId}")
    public String getSongsByAuthor(@PathVariable int authorId, Model model) {
        Author author = authorService.findAuthorById(authorId);
        List<Song> songs = songService.getSongsByAuthor(authorId);
        model.addAttribute("songs", songs);
        model.addAttribute("author", author);
        return "user/author/author-list";
    }
    @GetMapping("/singer/{singerId}/songs")
    public String getSongsBySingerId(@PathVariable("singerId") int singerId, Model model) {
        List<Song> songs = songService.findSongsBySingerId(singerId);
        model.addAttribute("songs", songs);
        return "user/singer/songs-by-singer";
    }

}
