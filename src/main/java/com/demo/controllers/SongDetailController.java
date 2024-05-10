package com.demo.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

import com.demo.entities.Author;
import com.demo.entities.Category;
import com.demo.entities.Singer;
import com.demo.entities.Song;
import com.demo.entities.Songdetail;
import com.demo.helpers.FileHelper;
import com.demo.services.AuthorService;
import com.demo.services.CategoryService;
import com.demo.services.SingerService;
import com.demo.services.SongDetailService;
import com.demo.services.SongService;

import jakarta.servlet.ServletContext;

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
    private static final String DIRECTORY = "C://Users//T14s//Desktop//app_web_music//target//classes//static//assets//music//";
    @GetMapping("add")
    public String add(ModelMap modelMap) {
        Songdetail songDetail = new Songdetail();
        Category category = new Category();
        Author author = new Author();
        Singer singer = new Singer();
        Song song = new Song();
        modelMap.put("songDetail", songDetail);
        modelMap.put("category", category);
        modelMap.put("author", author);
        modelMap.put("authors", authorService.findAll());
        modelMap.put("song", song);
        modelMap.put("singer", singer);
        modelMap.put("singers", singerService.findAll());
        modelMap.put("categories", categoryService.findAll());
        return "user/musicTest/add";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("songDetail") Songdetail songdetail,
    				  @ModelAttribute("category") Category category,
    				  @ModelAttribute("author") Author author,
                      @ModelAttribute("singer") Singer singer,
                      @RequestParam("fileImage") MultipartFile fileImage,
                      @RequestParam("fileMusic") MultipartFile fileMusic,
                      ModelMap modelMap,
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
                Category categoryObject = new Category();
                categoryObject = categoryService.find(category.getCategoryId());
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

                //lấy object Singer
//                Singer singerObject = new Singer();
//                singerObject = singerService.findSingerById(singer.getSingerId());
//                System.out.println("Singer Id "+ singerObject.getSingerId());
//                singer.setSingerName(String.valueOf(singerObject));


            	//lấy object songDetail vừa tạo
            	Songdetail songDetailObject = new Songdetail();
            	songDetailObject = songDetailService.findByFileUrlAndSongCoverUrl(songdetail.getFileUrl(), songdetail.getSongCoverUrl());
            	System.out.println("Song detail Id: " + songDetailObject.getSongDetailId());
            	song.setSongdetail(songDetailObject);

            	//end lấy object songDetail vừa tạo
            	//set default album
//            	Album albumObject = new Album();
//            	albumObject =
//            	song.setAlbum(null);
            	//end set default album

            	songService.save(song);

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
