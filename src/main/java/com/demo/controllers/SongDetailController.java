package com.demo.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    private static final String DIRECTORY = "C://Users//T14s//Desktop//app_web_music//target//classes//static//assets//music//";
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

//    @PostMapping("add")
//    public String add(@ModelAttribute("songDetail") Songdetail songdetail,
//    				  @ModelAttribute("category") Category category,
//                      @ModelAttribute("album") Album album,
//    				  @ModelAttribute("author") Author author,
//                      @ModelAttribute("singer") Singer singer,
//                      @RequestParam(value = "singerIds", required = false) List<Integer> singerIds,
//                      @RequestParam(value = "authorIds", required = false) List<Integer> authorIds,
//                      @RequestParam("accountId") int accountId,
//                      @RequestParam("fileImage") MultipartFile fileImage,
//                      @RequestParam("fileMusic") MultipartFile fileMusic,
//                      HttpSession httpSession,
//                      RedirectAttributes redirectAttributes) {
//        try {
//            //set file url + listenCount + songCoverURL (image)
//            try {
//    			File uploadFolderForMusic = new File(new ClassPathResource(".").getFile().getPath()
//                        + "/static/assets/music");
//    			if (!uploadFolderForMusic.exists()) {
//    				uploadFolderForMusic.mkdirs();
//    			}
//    			File uploadFolderForImage = new File(new ClassPathResource(".").getFile().getPath()
//                        + "/static/assets/images");
//    			if (!uploadFolderForImage.exists()) {
//    				uploadFolderForImage.mkdirs();
//    			}
//    			String FilenameForMusic = FileHelper.generateFileName(fileMusic.getOriginalFilename());
//    			String FilenameForImage = FileHelper.generateFileName(fileImage.getOriginalFilename());
//    			File saveFileForMusic = new ClassPathResource("static/assets/music").getFile();
//    			File saveFileForImage = new ClassPathResource("static/assets/images").getFile();
//    			Path pathForMusic = Paths.get(saveFileForMusic.getAbsolutePath() + File.separator + FilenameForMusic);
//    			Path pathForImage = Paths.get(saveFileForImage.getAbsolutePath() + File.separator + FilenameForImage);
//    			Files.copy(fileMusic.getInputStream(), pathForMusic, StandardCopyOption.REPLACE_EXISTING);
//    			Files.copy(fileImage.getInputStream(), pathForImage, StandardCopyOption.REPLACE_EXISTING);
//    			//http://localhost:8087/assets/music/ + tên image
//    			System.out.println("File name for music: " + FilenameForMusic);
//    			System.out.println("File name for image: " + FilenameForImage);
//    			System.out.println("File path for Music: " + pathForMusic);
//    			System.out.println("File path for Image: " + pathForImage);
//
//    			songdetail.setFileUrl(FilenameForMusic);
//                //cái này chỉ lưu file name. DB never save url b/c mỗi máy sẽ có 1 DIRECTORY khác nhau
//    			songdetail.setListenCount(1);
//    			songdetail.setSongCoverUrl(FilenameForImage);
//            } catch (Exception e) {
//    			e.printStackTrace();
//    		}
//            //end set file url + listenCount + songCoverURL (image)
//
//            // Lấy thông tin về người dùng đăng bài hát từ HttpSession
//            Account account = (Account) httpSession.getAttribute("loggedInUser");
//
//            if (songDetailService.save(songdetail)) {
//            	Song song = new Song();
//                //lấy object category
//                Category categoryObject = new Category();
//                categoryObject = categoryService.find(category.getCategoryId());
//                System.out.println("Cate Id: " + categoryObject.getCategoryId());
//                song.setCategory(categoryObject);
//                //end lấy object category
//
//                //lấy object author
//                Author authorObject = authorService.findAuthorByKeyword(author.getAuthorName());
//                if (authorObject != null) { // Kiểm tra xem tác giả đã tồn tại hay chưa
//                    System.out.println("Author found, Id: " + authorObject.getAuthorId());
//                    song.setAuthor(authorObject);
//                } else {
//                    // Tạo một tác giả mới nếu không tìm thấy
//                    if (authorService.save(author)) {
//                        Author newAuthor = authorService.findAuthorByKeyword(author.getAuthorName());
//                        if (newAuthor != null) {
//                            System.out.println("New Author created, Id: " + newAuthor.getAuthorId());
//                            song.setAuthor(newAuthor);
//                        } else {
//                            System.out.println("Failed to create new Author.");
//                        }
//                    } else {
//                        System.out.println("Failed to save Author.");
//                    }
//                }
//            	//end lấy object author
//
//
//            	//lấy object songDetail vừa tạo
//            	Songdetail songDetailObject = new Songdetail();
//            	songDetailObject = songDetailService.findByFileUrlAndSongCoverUrl(songdetail.getFileUrl(), songdetail.getSongCoverUrl());
//            	System.out.println("Song detail Id: " + songDetailObject.getSongDetailId());
//            	song.setSongdetail(songDetailObject);
//            	//end lấy object songDetail vừa tạo
//
//                Album albumObject = new Album();
//            	albumObject = albumService.find(album.getAlbumId());
//            	song.setAlbum(albumObject);
//            	//end set default album
//
//                // Lấy danh sách các ca sĩ và gắn vào bài hát
//                Set<Singer> singers = new HashSet<>();
//                if (singerIds != null) {
//                    for (Integer singerId : singerIds) {
//                        singer = singerService.findSingerById(singerId);
//                        if (singer != null) {
//                            singers.add(singer);
//                        }
//                    }
//                }
//                song.setSingers(singers);
//                // End lấy danh sách các ca sĩ và gắn vào bài hát
//                songService.save(song);
//
//                //Account account = accountService.find(accountId);
//
//                // Lưu thông tin về người dùng đăng bài hát vào bảng account_song
//                AccountSong accountSong = new AccountSong();
//                accountSong.setAccount(account);
//                accountSong.setSong(song);
//                accountSong.setPostDate(songdetail.getReleaseDate());
//                accountSongService.save(accountSong);
//
//
//            	return "redirect:/song/findAll";
//            } else {
//            	redirectAttributes.addFlashAttribute("msg", "Error");
//            	return "redirect:/songDetail/add";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            redirectAttributes.addFlashAttribute("msg", "Failed");
//            return "redirect:/song/findAll";
//        }
//    }
@PostMapping("add")
public String add(@ModelAttribute("songDetail") Songdetail songdetail,
                  @ModelAttribute("category") Category category,
                  @ModelAttribute("album") Album album,
                  @ModelAttribute("author") Author author,
                  @ModelAttribute("singer") Singer singer,
                  @RequestParam(value = "singerIds", required = false) List<Integer> singerIds,
                  @RequestParam(value = "authorIds", required = false) List<Integer> authorIds,
                  @RequestParam("fileImage") MultipartFile fileImage,
                  @RequestParam("fileMusic") MultipartFile fileMusic,
                  HttpSession session,
                  RedirectAttributes redirectAttributes) {
    try {
        //set file url + listenCount + songCoverURL (image)
        try {
            File uploadFolderForMusic = new File(new ClassPathResource(".").getFile().getPath()
                    + "/static/assets/music");
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
            File saveFileForMusic = new ClassPathResource("static/assets/music").getFile();
            File saveFileForImage = new ClassPathResource("static/assets/images").getFile();
            Path pathForMusic = Paths.get(saveFileForMusic.getAbsolutePath() + File.separator + FilenameForMusic);
            Path pathForImage = Paths.get(saveFileForImage.getAbsolutePath() + File.separator + FilenameForImage);
            Files.copy(fileMusic.getInputStream(), pathForMusic, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(fileImage.getInputStream(), pathForImage, StandardCopyOption.REPLACE_EXISTING);
            //http://localhost:8087/assets/music/ + tên image
            System.out.println("File name for music: " + FilenameForMusic);
            System.out.println("File name for image: " + FilenameForImage);
            System.out.println("File path for Music: " + pathForMusic);
            System.out.println("File path for Image: " + pathForImage);

            songdetail.setFileUrl(FilenameForMusic);
            //cái này chỉ lưu file name. DB never save url b/c mỗi máy sẽ có 1 DIRECTORY khác nhau
            songdetail.setListenCount(1);
            songdetail.setSongCoverUrl(FilenameForImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //end set file url + listenCount + songCoverURL (image)

        if (songDetailService.save(songdetail)) {
            Song song = new Song();
            //lấy object category
            Category categoryObject = categoryService.find(category.getCategoryId());
            System.out.println("Cate Id: " + categoryObject.getCategoryId());
            song.setCategory(categoryObject);
            //end lấy object category

            //lấy object author
            Author authorObject = authorService.findAuthorByKeyword(author.getAuthorName());
            if (authorObject != null) { // Kiểm tra xem tác giả đã tồn tại hay chưa
                System.out.println("Author found, Id: " + authorObject.getAuthorId());
                song.setAuthor(authorObject);
            } else {
                // Tạo một tác giả mới nếu không tìm thấy
                if (authorService.save(author)) {
                    Author newAuthor = authorService.findAuthorByKeyword(author.getAuthorName());
                    if (newAuthor != null) {
                        System.out.println("New Author created, Id: " + newAuthor.getAuthorId());
                        song.setAuthor(newAuthor);
                    } else {
                        System.out.println("Failed to create new Author.");
                    }
                } else {
                    System.out.println("Failed to save Author.");
                }
            }
            //end lấy object author

            //lấy object songDetail vừa tạo
            Songdetail songDetailObject = new Songdetail();
            songDetailObject = songDetailService.findByFileUrlAndSongCoverUrl(songdetail.getFileUrl(), songdetail.getSongCoverUrl());
            System.out.println("Song detail Id: " + songDetailObject.getSongDetailId());
            song.setSongdetail(songDetailObject);
            //end lấy object songDetail vừa tạo

            Album albumObject = new Album();
            albumObject = albumService.find(album.getAlbumId());
            song.setAlbum(albumObject);
            //end set default album

            // Lấy danh sách các ca sĩ và gắn vào bài hát
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
            // End lấy danh sách các ca sĩ và gắn vào bài hát

            songService.save(song);

            // Lưu thông tin về người dùng đăng bài hát vào bảng account_song
            Account loggedInUser = (Account) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                redirectAttributes.addFlashAttribute("msg", "Please login");
                return "redirect:/account/login";
            }
            int accountId = loggedInUser.getAccountId();

            AccountSong accountSong = new AccountSong();
            accountSong.setAccount(accountService.findById(accountId)); // Lưu accountId vào bảng Account_Song
            accountSong.setSong(song);
            accountSong.setPostDate(songdetail.getReleaseDate());
            accountSongService.save(accountSong);

            return "redirect:/song/findAll";
        } else {
            redirectAttributes.addFlashAttribute("msg", "Error");
            return "redirect:/songDetail/add";
        }

    } catch (Exception e) {
        e.printStackTrace();
        redirectAttributes.addFlashAttribute("msg", "Failed");
        return "redirect:/song/findAll";
    }
}

}
