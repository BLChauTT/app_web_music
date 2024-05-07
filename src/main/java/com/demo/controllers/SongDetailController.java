package com.demo.controllers;

import com.demo.entities.AccountSong;
import com.demo.entities.Songdetail;
import com.demo.helpers.FileHelper;
import com.demo.services.AccountSongService;
import com.demo.services.CategoryService;
import com.demo.services.SongDetailService;
import com.demo.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("songDetail")
public class SongDetailController {
    @Autowired
    private SongDetailService songDetailService;
    @Autowired
    private SongService songService;
    @Autowired
    private AccountSongService accountSongService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("add")
    public String add(ModelMap modelMap) {
        AccountSong newAccountSong = new AccountSong();
        Songdetail songDetail = new Songdetail();
        modelMap.put("songDetail", songDetail);
        //modelMap.put("accountSong", accountSongService);
        modelMap.put("categories", categoryService.findAll());
        return "user/musicTest/add";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("songDetail") Songdetail songdetail, @RequestParam("file") MultipartFile file,
                      RedirectAttributes redirectAttributes) {
        try {
            if (file.isEmpty()) {
                songdetail.setSongCoverUrl("no-image.jpg");
            } else {
                String fileName = FileHelper.generateFileName(file.getOriginalFilename());
                File imagesFolder = new ClassPathResource("static/assets/images").getFile();
                Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                songdetail.setSongCoverUrl(fileName);
            }
            if (songDetailService.save(songdetail)) {
                redirectAttributes.addFlashAttribute("msg", "Success");
                return "redirect:/song/findAll";
            } else {
                redirectAttributes.addFlashAttribute("msg", "Failed");
                return "redirect:/song/add";
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", "Failed");
            return "redirect:/song/add";
        }
    }
}
