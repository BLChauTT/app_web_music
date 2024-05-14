package com.demo.controllers;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.demo.services.AccountSongService;
import com.demo.services.AlbumService;
import com.demo.services.AuthorService;
import com.demo.services.SingerService;
import com.demo.services.SongDetailService;
import com.demo.services.SongService;
import com.demo.services.UserProfileService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("song")
public class SongController {
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
    public SongController(SongService _songService,
                          UserProfileService _userProfileService,
                          SingerService _singerService
                          ) {
        this.songService = _songService;
        this.userProfileService = _userProfileService;
        this.singerService = _singerService;
    }

//    @GetMapping({"index", "","/"})
//    public String song(ModelMap modelMap,
//                       @RequestParam(name = "keyword", required = false) String keyword,
//                       @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
//                       @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize,
//                       HttpSession httpSession,
//                       @RequestParam(value = "singerIds", required = false) List<Integer> singerIds) {
//        Account account = new Account();
//        Userprofile userprofile = new Userprofile();
//        Songdetail songdetail = new Songdetail();
//        Song hotSong = songService.findSongById(25);
//        List<Song> listSongs;
//        List<Album> listAlbums;
//        List<Singer> listSingers;
//        listSongs = songService.findSongsWithPagination(pageNo, pageSize);
//        listAlbums = albumService.findSongsWithPagination(pageNo,pageSize);
//        listSingers = singerService.findSingersWithPagination(pageNo, pageSize);
//
//        Account loggedInUser = (Account) httpSession.getAttribute("loggedInUser");
//        int accountId = loggedInUser.getAccountId();
//        AccountSong accountSong = new AccountSong();
//        accountSong.setAccount(accountJPAService.findById(accountId));
//        if (loggedInUser == null) {
//            return "redirect:/login";
//        }
//
//        // Xử lý tìm kiếm
//        List<Song> songLists;
//        if (keyword != null && !keyword.isEmpty()) {
//            songLists = songService.findByTitleContainingIgnoreCase(keyword);
//        } else {
//            songLists = songService.findSongsWithPagination(pageNo, pageSize);
//        }
//
//        // Thêm vào modelMap
//        modelMap.put("songLists", songLists);
//
//        // Lấy danh sách ca sĩ
//        Set<Singer> singers = new HashSet<>();
//        if (!songLists.isEmpty()) {
//            int firstSongId = songLists.get(0).getSongId();
//            singers = singerService.findSingersBySongId(firstSongId);
//        }
//        Singer singer = singers.isEmpty() ? new Singer() : singers.iterator().next();
//        for (Song song:songLists) {
//            song.getSongdetail().getSongCoverUrl();
//        }
//        modelMap.put("singer", singer);
//
//        List<Singer> singerList = singerService.findSingersWithPagination(pageNo, pageSize);
//        modelMap.put("singerList", singerList);
//
//        // Lấy danh sách album
//        List<Album> albumList = albumService.findSongsWithPagination(pageNo, pageSize);
//        modelMap.put("albumList", albumList);
//
//        // Lấy danh sách chi tiết bài hát
//        List<Songdetail> songdetailList = songDetailService.findSongsWithPagination(pageNo, pageSize);
//        modelMap.put("songdetailList", songdetailList);
//
//        // Phân trang
//        long totalSongs = songService.countTotalSongs();
//        int totalPages = (int) Math.ceil((double) totalSongs / pageSize);
//        modelMap.addAttribute("totalSongs", totalSongs);
//        modelMap.addAttribute("totalPages", totalPages);
//        modelMap.addAttribute("pageNo", pageNo);
//        modelMap.addAttribute("pageSize", pageSize);
//
//        return "user/music";
//    }

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
        Song hotSong = songService.findSongById(25);
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

        String imageUrl = environment.getProperty("imageUrl");
        modelMap.put("imageUrl", imageUrl);
//        String fileImageUrl = "no-image.jpg"; //set động
//        String urlImage = imageUrl + fileImageUrl;
//        modelMap.put("urlImage", urlImage);

        modelMap.put("loggedInUser", loggedInUser);
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
    public String cat(ModelMap modelMap,
                      @RequestParam(name = "keyword", required = false) String keyword,
                      HttpSession httpSession,
                      @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                      @RequestParam(value = "pageSize", defaultValue = "18", required = false) int pageSize
                      ) {
        //lấy session
        Account account = new Account();
        AccountSong accountSong = new AccountSong();
        Userprofile userprofile = new Userprofile();
        Account loggedInUser = (Account) httpSession.getAttribute("loggedInUser");
        int accountId = loggedInUser.getAccountId();
        accountSong.setAccount(accountJPAService.findById(accountId));
        if (loggedInUser == null) {
            return "redirect:/account/login";
        }

        //lấy ảnh
        String imageUrl = environment.getProperty("imageUrl");
        modelMap.put("imageUrl", imageUrl);

        // Xử lý tìm kiếm
        List<Song> songLists;
        if (keyword != null && !keyword.isEmpty()) {
            songLists = songService.findByTitleContainingIgnoreCase(keyword);
        } else {
            songLists = songService.findSongsWithPagination(pageNo, pageSize);
        }
        modelMap.put("songLists", songLists);

        // Lấy danh sách ca sĩ
        Set<Singer> singers = new HashSet<>();
        if (!songLists.isEmpty()) {
            int firstSongId = songLists.get(0).getSongId();
            singers = singerService.findSingersBySongId(firstSongId);
        }
        Singer singer = singers.isEmpty() ? new Singer() : singers.iterator().next();
        for (Song song:songLists) {
            song.getSongdetail().getSongCoverUrl();
        }
        modelMap.put("singer", singer);

        List<Singer> singerList = singerService.findSingersWithPagination(pageNo, pageSize);
        modelMap.put("singerList", singerList);

        // Lấy danh sách album
        List<Album> albumList = albumService.findSongsWithPagination(pageNo, pageSize);
        modelMap.put("albumList", albumList);

        // Lấy danh sách chi tiết bài hát
        List<Songdetail> songdetailList = songDetailService.findSongsWithPagination(pageNo, pageSize);
        modelMap.put("songdetailList", songdetailList);

        // Phân trang
        long totalSongs = songService.countTotalSongs();
        int totalPages = (int) Math.ceil((double) totalSongs / pageSize);
        modelMap.addAttribute("totalSongs", totalSongs);
        modelMap.addAttribute("totalPages", totalPages);
        modelMap.addAttribute("pageNo", pageNo);
        modelMap.addAttribute("pageSize", pageSize);

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

        //bài chính
        String musicUrl = environment.getProperty("musicUrl");
        String fileUrl = songService.findFileUrlBySongId(id);
        String url = musicUrl + fileUrl;
        modelMap.put("urlMusic", url);
        songdetail.setFileUrl(fileUrl);

        String imageUrl = environment.getProperty("imageUrl");
        String fileImageUrl = songService.findSongCoverUrlBySongId(id);
        System.out.println(fileImageUrl);
        String urlImage = imageUrl + fileImageUrl;
        modelMap.put("urlImage", urlImage);

//        String imageUrl = environment.getProperty("imageUrl");
        modelMap.put("imageUrl", imageUrl);

        modelMap.put("userprofile", userprofile);
        modelMap.put("musicUrl", musicUrl);
        modelMap.put("accountSong", accountSong);
        modelMap.put("account", account);
        modelMap.put("songdetail", songdetail);
        modelMap.put("loggedInUser", loggedInUser);
        modelMap.put("song", songService.findSongById(id));

        //Phần bên dưới
        List<Song> listSongs;
        List<Album> listAlbums;
        List<Singer> listSingers;
        listSongs = songService.findSongsWithPagination(pageNo, pageSize);
        listAlbums = albumService.findSongsWithPagination(pageNo,pageSize);
        listSingers = singerService.findSingersWithPagination(pageNo, pageSize);

        Set<Singer> singers = new HashSet<>();
        if (!listSongs.isEmpty()) {
            int firstSongId = listSongs.get(0).getSongId();
            singers = singerService.findSingersBySongId(firstSongId);
        }
        Singer singer = singers.isEmpty() ? new Singer() : singers.iterator().next();
        for (Song song:listSongs) {
            song.getSongdetail().getSongCoverUrl();
        }
        modelMap.put("accountSong", accountSong);
        modelMap.put("account", account);
        modelMap.put("listSingers", listSingers);
        modelMap.put("listAlbums", listAlbums);
        modelMap.put("listSongs", listSongs);
        modelMap.put("singer", singer);
        modelMap.put("songs", songService.findAll());
        modelMap.put("albums", albumService.findAll());

        System.out.println("fileURL: " + fileUrl);
        System.out.println("musicURL: " +musicUrl );
        System.out.println();

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

    @GetMapping("download")
    public String download() {
        System.out.println("Call");
        return "file/download";
    }

    //đang lấy tên file mặc định cho trước, anh em sửa thì fileName từ db vào là được
//    @GetMapping("/downloadFile")
//    public ResponseEntity<InputStreamResource> downloadFile1(@RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName)
//            throws IOException {
//        MediaType mediaType = getMediaTypeForFileName(context, fileName);
//        System.out.println("fileName: " + fileName);
//        System.out.println("mediaType: " + mediaType);
//
//        File file = new File(DIRECTORY + "/" + fileName);
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//        return ResponseEntity.ok()
//                // Content-Disposition
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
//                // Content-Type
//                .contentType(mediaType)
//                // Contet-Length
//                .contentLength(file.length()) //
//                .body(resource);
//    }
//
//    private MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
//        // application/pdf
//        // application/xml
//        // image/gif, ...
//        String mineType = servletContext.getMimeType(fileName);
//        try {
//            MediaType mediaType = MediaType.parseMediaType(mineType);
//            return mediaType;
//        } catch (Exception e) {
//            return MediaType.APPLICATION_OCTET_STREAM;
//        }
//    }
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = new FileSystemResource(Paths.get(UPLOAD_DIR).resolve(fileName).toAbsolutePath().toString());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
