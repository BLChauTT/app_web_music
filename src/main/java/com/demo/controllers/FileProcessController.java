package com.demo.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.helpers.FileHelper;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("file")
public class FileProcessController {

	private static final String DIRECTORY = "D:\\DoAnKy4\\app_web_music\\target\\classes\\static\\assets";
    private static final String DEFAULT_FILE_NAME = "";

    @Autowired
    ServletContext context;

	@GetMapping({"upload", "/", ""})
	public String index() {
		System.out.println("Call");
		return "file/upload";
	}

	@PostMapping("upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "File is Empty");
			return "redirect:/file/upload";
		}
		try {
			File uploadFolder = new File(new ClassPathResource(".").getFile().getPath() + "/static/assets/music");
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}
			String Filename = FileHelper.generateFileName(file.getOriginalFilename());
			File saveFile = new ClassPathResource("static/assets/music").getFile();
			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + Filename);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			//http://localhost:8087/assets/music/ + tên image
			System.out.println("File name: " + Filename);
			System.out.println("File path: " + path);
			redirectAttributes.addFlashAttribute("msg", "Upload '" + file.getOriginalFilename() + "' successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/file/upload";
	}

	@GetMapping("download")
	public String download() {
		System.out.println("Call");
		return "file/download";
	}

	//đang lấy tên file mặc định cho trước, anh em sửa thì fileName từ db vào là được
	@GetMapping("/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile1(
			@RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {
        MediaType mediaType = getMediaTypeForFileName(context, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        File file = new File(DIRECTORY + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
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
