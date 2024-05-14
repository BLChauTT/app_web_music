package com.demo.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import com.demo.entities.Singer;
import com.demo.entities.Song;
import com.demo.entities.Songdetail;
import com.demo.entities.Userprofile;
import com.demo.helpers.FileHelper;
import com.demo.services.AccountJPAService;
import com.demo.services.AccountSongService;
import com.demo.services.AlbumService;
import com.demo.services.AuthorService;
import com.demo.services.SingerService;
import com.demo.services.SongDetailService;
import com.demo.services.SongService;
import com.demo.services.UserProfileService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("singer")
public class SingerController {

    private static final String DIRECTORY = "D:\\DoAnKy4\\app_web_music\\target\\classes\\static\\assets";
    private static final String DEFAULT_FILE_NAME = "";
    private static final String UPLOAD_DIR = "src/main/resources/static/assets/musics";
    private static final Logger logger = LoggerFactory.getLogger(SongController.class);
    @Autowired
    private SongService songService;
    @Autowired
    private AccountSongService accountSongService;
    @Autowired
    private SongDetailService songDetailService;
    @Autowired
    private SingerService singerService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AccountJPAService accountJPAService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private Environment environment;
    @Autowired
    ServletContext context;

    @Autowired
    public SingerController(SingerService singerService,
            UserProfileService _userProfileService,
            SongService _songService) {
        this.singerService = singerService;
        this.songService = _songService;
        this.userProfileService = _userProfileService;
    }
    @GetMapping("singers")
    public String singer(ModelMap modelMap) {
        modelMap.put("singers", singerService.findAll());
        return "user/singer/singerFindAll";
    }

    @GetMapping({ "cat", "filter" })
    public String cat(ModelMap modelMap,
            @RequestParam(name = "keyword", required = false) String keyword,
            HttpSession httpSession,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "12", required = false) int pageSize) {
        // Lấy session
        Account loggedInUser = (Account) httpSession.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/account/login";
        }
        int accountId = loggedInUser.getAccountId();
        AccountSong accountSong = new AccountSong();
        accountSong.setAccount(accountJPAService.findById(accountId));

        // Lấy ảnh
        String imageUrl = environment.getProperty("imageUrl");
        modelMap.put("imageUrl", imageUrl);

        // Xử lý tìm kiếm bài hát
        List<Song> songList = songService.findSongsWithPagination(pageNo, pageSize);
        modelMap.put("songLists", songList);

        // Xử lý tìm kiếm ca sĩ
        List<Singer> singerList;
        if (keyword != null && !keyword.isEmpty()) {
            singerList = singerService.findByNameContainingIgnoreCase(keyword);
        } else {
            singerList = singerService.findSingersWithPagination(pageNo, pageSize);
        }
        modelMap.put("singerList", singerList);

        // Lấy danh sách album
        List<Album> albumList = albumService.findSongsWithPagination(pageNo, pageSize);
        modelMap.put("albumList", albumList);

        // Lấy danh sách chi tiết bài hát
        List<Songdetail> songdetailList = songDetailService.findSongsWithPagination(pageNo, pageSize);
        modelMap.put("songdetailList", songdetailList);

        // Phân trang
        long totalSingers = singerService.countTotalSingers();
        int totalPages = (int) Math.ceil((double) totalSingers / pageSize);
        modelMap.addAttribute("totalSingers", totalSingers);
        modelMap.addAttribute("totalPages", totalPages);
        modelMap.addAttribute("pageNo", pageNo);
        modelMap.addAttribute("pageSize", pageSize);

        return "user/music.artists";
    }

    @GetMapping({"detail/{id}"})
    public String detail(@PathVariable("id") int id, ModelMap modelMap,
                         HttpSession httpSession,
                         @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                         @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize) {

        Songdetail songdetail = new Songdetail();
        AccountSong accountSong = new AccountSong();
        Account account = new Account();
        Userprofile userprofile = new Userprofile();
        Account loggedInUser = (Account) httpSession.getAttribute("loggedInUser");
        int accountId = loggedInUser.getAccountId();
        accountSong.setAccount(accountJPAService.findById(accountId));

        String imageUrl = environment.getProperty("imageUrl");
        String fileImageUrl = singerService.findSingerCoverUrlBySingerId(id);
        System.out.println(fileImageUrl);
        String urlImage = imageUrl + fileImageUrl;

        Singer singer = singerService.findSingerById(id);
        List<Song> singerSongsBySingers = songService.findSongsBySingerId(id);

        modelMap.put("singerSongsBySingers", singerSongsBySingers);
        modelMap.put("imageUrl", imageUrl);
        modelMap.put("urlImage", urlImage);
        modelMap.put("userprofile", userprofile);
        modelMap.put("songdetail", songdetail);
        modelMap.put("loggedInUser", loggedInUser);
        modelMap.put("singer", singer);

        //Phần bên dưới
        List<Song> listSongs;
        List<Album> listAlbums;
        List<Singer> listSingers;
        listSongs = songService.findSongsWithPagination(pageNo, pageSize);
        listAlbums = albumService.findSongsWithPagination(pageNo,pageSize);
        listSingers = singerService.findSingersWithPagination(pageNo, pageSize);

        modelMap.put("accountSong", accountSong);
        modelMap.put("account", account);
        modelMap.put("listSingers", listSingers);
        modelMap.put("listAlbums", listAlbums);
        modelMap.put("listSongs", listSongs);
        modelMap.put("songs", songService.findAll());
        modelMap.put("albums", albumService.findAll());

        //Phân trang bài ca sĩ hát
        long totalSongsBySinger = songService.countTotalSongs();
        int totalPagesSongsBySinger = (int) Math.ceil((double) totalSongsBySinger / pageSize);
        modelMap.addAttribute("totalSongsBySinger", totalSongsBySinger);
        modelMap.addAttribute("totalPagesSongsBySinger", totalPagesSongsBySinger);
        modelMap.addAttribute("pageNo", pageNo);
        modelMap.addAttribute("pageSize", pageSize);

        return "user/music.artist";
    }
    @GetMapping("findAll")
    public String findAll(ModelMap modelMap) {
        modelMap.put("singers", singerService.findAll());
        return "user/singer/singerFindAll";
    }

    @GetMapping("details/{id}")
    public String details(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("singer", singerService.findSingerById(id));
        return "user/singer/singerDetail";
    }



    @GetMapping("add")
    public String add(ModelMap modelMap) {
        modelMap.put("Singer", new Singer());
        return "user/singer/add";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("singer") Singer singer, @RequestParam("fileImage") MultipartFile fileImage,
            RedirectAttributes redirectAttributes) throws Exception {
        try {
            if (fileImage.isEmpty()) {
                singer.setSingerAvatarUrl("no-image.jpg");
            } else {
                String fileName = FileHelper.generateFileName(fileImage.getOriginalFilename());
                File imagesFolder = new ClassPathResource("static/assets/images").getFile();
                Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
                System.out.println(path.toString());
                Files.copy(fileImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                singer.setSingerAvatarUrl(fileName);
            }
            if (singerService.save(singer)) {
                redirectAttributes.addFlashAttribute("msg", "Success");
                return "redirect:/singer/cat";
            } else {
                redirectAttributes.addFlashAttribute("msg", "Failed");
                return "redirect:/singer/add";
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
            return "redirect:/singer/add";
        }
    }

}
