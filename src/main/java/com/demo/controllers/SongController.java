package com.demo.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entities.Account;
import com.demo.entities.AccountSong;
import com.demo.entities.Album;
import com.demo.entities.Author;
import com.demo.entities.Singer;
import com.demo.entities.Song;
import com.demo.entities.Songdetail;
import com.demo.entities.Userprofile;
import com.demo.services.AccountJPAService;
import com.demo.services.AlbumService;
import com.demo.services.AuthorService;
import com.demo.services.SingerService;
import com.demo.services.SongService;
import com.demo.services.UserProfileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("song")
public class SongController {
    private static final String DIRECTORY = "D:\\DoAnKy4\\app_web_music\\target\\classes\\static\\assets";
    private static final String DEFAULT_FILE_NAME = "";
    @Autowired
    private SongService songService;
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
    public SongController(SongService _songService,
                          UserProfileService _userProfileService,
                          SingerService _singerService
                          ) {
        this.songService = _songService;
        this.userProfileService = _userProfileService;
        this.singerService = _singerService;
    }

    @GetMapping({"index", "","/"})
    public String song(ModelMap modelMap,
                       @RequestParam(name = "keyword", required = false) String keyword,
                       @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize,
                       HttpSession httpSession,
                       @RequestParam(value = "singerIds", required = false) List<Integer> singerIds) {
        Song song = new Song();
        Account account = new Account();
        Userprofile userprofile = new Userprofile();
        Songdetail songdetail = new Songdetail();
        Song hotSong = songService.findSongById(24);
        List<Song> listSongs;
        List<Album> listAlbums;
        List<Singer> listSingers;
        listSongs = songService.findSongsWithPagination(pageNo, pageSize);
        listAlbums = albumService.findSongsWithPagination(pageNo,pageSize);
        listSingers = singerService.findSingersWithPagination(pageNo, pageSize);

        Account loggedInUser = (Account) httpSession.getAttribute("loggedInUser");
        int accountId = loggedInUser.getAccountId();
        AccountSong accountSong = new AccountSong();
        accountSong.setAccount(accountJPAService.findById(accountId));


        Set<Singer> singers = new HashSet<>();
        if (!listSongs.isEmpty()) {
            // Lấy ra id của bài hát đầu tiên trong danh sách
            int firstSongId = listSongs.get(0).getSongId();
            // Tìm các ca sĩ của bài hát đầu tiên
            singers = singerService.findSingersBySongId(firstSongId);
        }
        // Lấy ra một Singer từ Set singers
        Singer singer = singers.isEmpty() ? new Singer() : singers.iterator().next();

        modelMap.put("userprofile", userprofile);
        modelMap.put("accountSong", accountSong);
        modelMap.put("account", account);
        modelMap.put("listSingers", listSingers);
        modelMap.put("listAlbums", listAlbums);
        modelMap.put("listSongs", listSongs);
        modelMap.put("song", song);
        modelMap.put("singer", singer);
        modelMap.put("songdetail", songdetail);
        modelMap.put("songs", songService.findAll());
        modelMap.put("hotSong", hotSong.getSongdetail());
        modelMap.put("albums", albumService.findAll());


        return "user/music";
    }
    @GetMapping({"cat","filter"})
    public String cat(ModelMap modelMap) {
        return "user/music.cat";
    }
    @GetMapping({"artist","allsinger"})
    public String artist(ModelMap modelMap) {
        return "user/music.artist";
    }
    @GetMapping({"detailMusic"})
    public String detailMusic(ModelMap modelMap) {
        return "user/music.detail";
    }
    @GetMapping({"detail/{id}"})
    public String detail(@PathVariable("id") int id, ModelMap modelMap,
                         HttpSession httpSession) {
        Songdetail songdetail = new Songdetail();
        AccountSong accountSong = new AccountSong();
        Account account = new Account();
        Userprofile userprofile = new Userprofile();
        Account loggedInUser = (Account) httpSession.getAttribute("loggedInUser");
        int accountId = loggedInUser.getAccountId();
        accountSong.setAccount(accountJPAService.findById(accountId));

        modelMap.put("userprofile", userprofile);
        modelMap.put("accountSong", accountSong);
        modelMap.put("account", account);
        modelMap.put("songdetail", songdetail);
        modelMap.put("loggedInUser", loggedInUser);
        modelMap.put("song", songService.findSongById(id));
        return "user/music.detail";
    }
    @GetMapping("findAll")
    public String findAll(ModelMap modelMap) {
        modelMap.put("songs", songService.findAll());
        modelMap.put("singers", singerService.findAll());
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
//        List<Song> songs = songService.findSongsBySingerId(singerId);
//        model.addAttribute("songs", songs);
        return "user/singer/songs-by-singer";
    }
    @GetMapping("/song/search")
    public String search(Model model, @RequestParam("title") String title) {
        List<Song> songs = songService.findByTitleContainingIgnoreCase(title);
        model.addAttribute("songs", songs);
        return "user/musicTest/search";
    }
}
