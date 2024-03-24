package com.demo.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

import com.demo.entitiesjpa.Product;
import com.demo.helpers.FileHelper;
import com.demo.services.CategoryService;
import com.demo.services.ProductJPAService;

@Controller
@RequestMapping("productjpa")
public class ProductJPAController {

	@Autowired
	private ProductJPAService productJPAService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping({ "", "index", "/" })
	public String index(ModelMap modelMap) {
		modelMap.put("products", productJPAService.findAll());
		return "productjpa/index";
	}

	@GetMapping("index2")
	public String index2(ModelMap modelMap) {
		modelMap.put("products", productJPAService.findByKeyword("tab"));
		return "productjpa/index";
	}

	@GetMapping("index3")
	public String index3(ModelMap modelMap) {
		modelMap.put("products", productJPAService.findByPrices(5, 10));
		return "productjpa/index";
	}

	@GetMapping("index4")
	public String index4(ModelMap modelMap) {
		modelMap.put("products", productJPAService.findByDates("15/12/2023", "25/12/2023"));
		return "productjpa/index";
	}

	@GetMapping("index5")
	public String index5(ModelMap modelMap) {
		modelMap.put("sum1", productJPAService.sum1());
		return "productjpa/index2";
	}

	@GetMapping("add")
	public String add(ModelMap modelMap) {
		Product product = new Product();
		modelMap.put("product", product);
		modelMap.put("categories", categoryService.findAll());
		return "productjpa/add";
	}

	@PostMapping("add")
	public String add(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		try {
			if (file.isEmpty()) {
				product.setPhoto("no-image.jpg");
			} else {
				String fileName = FileHelper.generateFileName(file.getOriginalFilename());
				File imagesFolder = new ClassPathResource("static/assets/images").getFile();
				Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				product.setPhoto(fileName);
			}
			if (productJPAService.save(product)) {
				redirectAttributes.addFlashAttribute("msg", "Success");
				return "redirect:/productjpa/index";
			} else {
				redirectAttributes.addFlashAttribute("msg", "Failed");
				return "redirect:/productjpa/add";
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msg", "Failed");
			return "redirect:/productjpa/add";
		}
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("product", productJPAService.find(id));
		modelMap.put("categories", categoryService.findAll());
		return "productjpa/edit";
	}

	@PostMapping("edit")
	public String edit(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		try {
			if (!file.isEmpty()) {
				String fileName = FileHelper.generateFileName(file.getOriginalFilename());
				File imagesFolder = new ClassPathResource("static/assets/images").getFile();
				Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				product.setPhoto(fileName);
			}
			if (productJPAService.save(product)) {
				redirectAttributes.addFlashAttribute("msg", "Success");
				return "redirect:/productjpa/index";
			} else {
				redirectAttributes.addFlashAttribute("msg", "Failed");
				return "redirect:/productjpa/edit/" + product.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msg", "Failed");
			return "redirect:/productjpa/edit/" + product.getId();
		}
	}

}
