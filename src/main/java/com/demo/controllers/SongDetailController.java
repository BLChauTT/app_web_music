package com.demo.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import com.demo.entities.Category;
import com.demo.entities.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.AccountSong;
import com.demo.entities.Songdetail;
import com.demo.helpers.FileHelper;
import com.demo.services.AccountSongService;
import com.demo.services.CategoryService;
import com.demo.services.SongDetailService;
import com.demo.services.SongService;

import jakarta.servlet.ServletContext;

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
    private static final String DIRECTORY = "D:\\DoAnKy4\\app_web_music\\target\\classes\\static\\assets";
    private static final String DEFAULT_FILE_NAME = "";
    @GetMapping("add")
    public String add(ModelMap modelMap) {
        AccountSong newAccountSong = new AccountSong();
        Songdetail songDetail = new Songdetail();
        modelMap.put("songDetail", songDetail);
        modelMap.put("categories", categoryService.findAll());
        return "user/musicTest/add";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("songDetail") Songdetail songdetail,
                      @RequestParam("fileImage") MultipartFile fileImage,
                      @RequestParam("fileMusic") MultipartFile fileMusic,
                      RedirectAttributes redirectAttributes) {
        try {
            if (fileImage.isEmpty()) {
                songdetail.setSongCoverUrl("no-image.jpg");
            } else if (fileMusic.isEmpty()) {
                redirectAttributes.addFlashAttribute("msg", "File is Empty");
                return "redirect:/songDetail/add";
            } else {
                boolean savedSongDetail = songDetailService.save(songdetail);
                Integer songDetailId = savedSongDetail.getId();

                Integer accountId = (Integer) request.getSession().getAttribute("accountId");

                AccountSong accountSong = new AccountSong();
                accountSong.setAccount(accountId);
                accountSong.setSong(songDetailId);
                accountSongService.save(accountSong);
                //category
                Song song = (Song) songdetail.getSongs();
                Category category = song.getCategory();

                String fileNameImage = FileHelper.generateFileName(fileImage.getOriginalFilename());
                String fileNameMusic = FileHelper.generateFileName(fileMusic.getOriginalFilename());
                String musicsFolder = "assets/musics";

                String absolutePath = Paths.get(DIRECTORY, musicsFolder).toString();
                File songsFolder = new File(absolutePath);

                File imagesFolder = new ClassPathResource("static/assets/images").getFile();
                //File songsFolder = new ClassPathResource("static/assets/musics").getFile();

                Path pathImage = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileNameImage);
                Path pathMusic = Paths.get(songsFolder.getAbsolutePath() + File.separator + fileNameMusic);

                Files.copy(fileImage.getInputStream(), pathImage, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(fileMusic.getInputStream(), pathMusic, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("File name: " + fileNameMusic);
                System.out.println("File path: " + pathMusic);

                songdetail.setSongCoverUrl(fileNameImage);
                songdetail.setReleaseDate(new Date());
                songdetail.setFileUrl(pathMusic.toString());
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


    private MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        // application/pdf
        // application/xml
        // image/gif, ...
        String mineType = servletContext.getMimeType(fileName);
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
