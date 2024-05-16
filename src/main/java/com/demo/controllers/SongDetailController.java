package com.demo.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.AccountSong;
import com.demo.entities.Album;
import com.demo.entities.Author;
import com.demo.entities.Category;
import com.demo.entities.Singer;
import com.demo.entities.Song;
import com.demo.entities.Songdetail;
import com.demo.helpers.FileHelper;
import com.demo.services.AccountJPAService;
import com.demo.services.AccountSongService;
import com.demo.services.AlbumService;
import com.demo.services.AuthorService;
import com.demo.services.CategoryService;
import com.demo.services.SingerService;
import com.demo.services.SongDetailService;
import com.demo.services.SongService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("songDetail")
public class SongDetailController {
    @Autowired
    private SongDetailService songDetailService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SongService songService;
    @Autowired
    private SingerService singerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AccountSongService accountSongService;
    @Autowired
    private AccountJPAService accountService;

    private static final String DIRECTORY = "D:\\DoAnKy4\\app_web_music\\target\\classes\\static\\assets";
    private static final String DEFAULT_FILE_NAME = "";
    @GetMapping("add")
    public String add(ModelMap modelMap, HttpSession httpSession) {
        Songdetail songDetail = new Songdetail();
        Category category = new Category();
        Author author = new Author();
        Singer singer = new Singer();
        Song song = new Song();
        Album album = new Album();
        modelMap.put("album", album);
        modelMap.put("songDetail", songDetail);
        modelMap.put("category", category);
        modelMap.put("author", author);
        modelMap.put("authors", authorService.findAll());
        modelMap.put("albums", albumService.findAll());
        modelMap.put("song", song);
        modelMap.put("singer", singer);
        modelMap.put("singers", singerService.findAll());
        modelMap.put("categories", categoryService.findAll());
        // Lấy thông tin về người dùng đăng nhập từ HttpSession
        Account loggedInUser = (Account) httpSession.getAttribute("loggedInUser");
        modelMap.put("loggedInUser", loggedInUser);
        return "user/musicTest/add";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("songDetail") Songdetail songdetail,
                      @ModelAttribute("category") Category category,
                      @ModelAttribute("album") Album album,
                      @ModelAttribute("author") Author author,
                      @ModelAttribute("singer") Singer singer,
                      @RequestParam(value = "singerIds", required = false) List<Integer> singerIds,
                      @RequestParam("fileImage") MultipartFile fileImage,
                      @RequestParam("fileMusic") MultipartFile fileMusic,
                      HttpSession httpSession,
                      RedirectAttributes redirectAttributes) {

        if(fileImage.isEmpty() && fileMusic.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "File is Empty");
            return "redirect:/songDetail/add";
        }

        try {
            //set file url + listenCount + songCoverURL (image)
            try {
                File uploadFolderForMusic = new File(new ClassPathResource(".").getFile().getPath()
                        + "/static/assets/musics");
                if (!uploadFolderForMusic.exists()) {
                    uploadFolderForMusic.mkdirs();
                }
                File uploadFolderForImage = new File(new ClassPathResource(".").getFile().getPath()
                        + "/static/assets/images");
                if (!uploadFolderForImage.exists()) {
                    uploadFolderForImage.mkdirs();
                }
                String FilenameForMusic = FileHelper.generateFileName(fileMusic.getOriginalFilename());
                String FilenameForImage = FileHelper.generateFileName(fileImage.getOriginalFilename());
                File saveFileForMusic = new ClassPathResource("static/assets/musics").getFile();
                File saveFileForImage = new ClassPathResource("static/assets/images").getFile();
                Path pathForMusic = Paths.get(saveFileForMusic.getAbsolutePath() + File.separator + FilenameForMusic);
                Path pathForImage = Paths.get(saveFileForImage.getAbsolutePath() + File.separator + FilenameForImage);
                Files.copy(fileMusic.getInputStream(), pathForMusic, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(fileImage.getInputStream(), pathForImage, StandardCopyOption.REPLACE_EXISTING);

                songdetail.setFileUrl(FilenameForMusic);
                Date today = new Date();
                songdetail.setReleaseDate(today);
                songdetail.setListenCount(1);
                songdetail.setSongCoverUrl(FilenameForImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //end set file url + listenCount + songCoverURL (image)

            if (songDetailService.save(songdetail)) {
                Song song = new Song();

                Category categoryObject = categoryService.find(category.getCategoryId());
                song.setCategory(categoryObject);

                Author authorObject = authorService.findAuthorByKeyword(author.getAuthorName());
                if (authorObject != null) {
                    song.setAuthor(authorObject);
                } else {
                    if (authorService.save(author)) {
                        authorObject = authorService.findAuthorByKeyword(author.getAuthorName());
                        if (authorObject != null) {
                            song.setAuthor(authorObject);
                        } else {
                            System.out.println("Failed to create new Author.");
                        }
                    } else {
                        System.out.println("Failed to save Author.");
                    }
                }

                Songdetail songDetailObject = songDetailService.findByFileUrlAndSongCoverUrl(songdetail.getFileUrl(), songdetail.getSongCoverUrl());
                song.setSongdetail(songDetailObject);

                Album albumObject = albumService.find(album.getAlbumId());
                song.setAlbum(albumObject);

                Set<Singer> singers = new HashSet<>();
                if (singerIds != null) {
                    for (Integer singerId : singerIds) {
                        singer = singerService.findSingerById(singerId);
                        if (singer != null) {
                            singers.add(singer);
                        }
                    }
                }
                song.setSingers(singers);

                songService.save(song);

                Account loggedInUser = (Account) httpSession.getAttribute("loggedInUser");
                if (loggedInUser == null) {
                    redirectAttributes.addFlashAttribute("msg", "Please login");
                    return "redirect:/account/login";
                }
                int accountId = loggedInUser.getAccountId();

                AccountSong accountSong = new AccountSong();
                accountSong.setAccount(accountService.findById(accountId));
                accountSong.setSong(song);
                accountSong.setPostDate(songdetail.getReleaseDate());
                accountSongService.save(accountSong);

                redirectAttributes.addFlashAttribute("msg", "Song added successfully");
                return "redirect:/song/cat";
            } else {
                redirectAttributes.addFlashAttribute("msg", "Error");
                return "redirect:/songDetail/add";
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", "Failed");
            return "redirect:/song/cat";
        }
    }

    @GetMapping("musicDetail")
	public String SongDetail() {

		return "user/musicDetail/find";
	}
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, ModelMap modelMap) {
        Songdetail songDetail = songDetailService.find(id);
        if (songDetail == null) {
            return "user/musicTest/edit";
        }
        modelMap.addAttribute("songDetail", songDetail);
        return "user/musicTest/edit";
    }

    // Xử lý khi submit form chỉnh sửa thông tin bài hát
    @PostMapping("/edit")
    public String edit(@ModelAttribute("songDetail") Songdetail updatedSongDetail, ModelMap modelMap) {
        Songdetail songDetail = songDetailService.find(updatedSongDetail.getSongDetailId());
        if (songDetail == null) {
            return "user/musicTest/edit";
        }
        // Cập nhật thông tin bài hát
        songDetail.setTitle(updatedSongDetail.getTitle());
        songDetailService.save(songDetail);
        modelMap.addAttribute("songDetail", songDetail);
        return "redirect:/song/cat";
    }

}
