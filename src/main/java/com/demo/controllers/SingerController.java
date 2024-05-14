package com.demo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entities.Account;
import com.demo.entities.AccountSong;
import com.demo.entities.Album;
import com.demo.entities.Singer;
import com.demo.entities.Song;
import com.demo.entities.Songdetail;
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

    @GetMapping({"cat", "filter"})
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
}
