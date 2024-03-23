package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dtos.CategoryDTO;
import com.demo.dtos.ProductDTO;
import com.demo.entitiesjpa.Category;
import com.demo.services.CategoryService;
import com.demo.services.ProductJPAService;

@RestController
@RequestMapping("ajaxjpa")
public class AjaxJPAController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductJPAService productJPAService;

	@GetMapping(value = "demo1/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public CategoryDTO demo1(@PathVariable("id") int id) {
		return categoryService.findDTO(id);
	}

	@GetMapping(value = "demo2", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public List<CategoryDTO> demo2() {
		return categoryService.findAllDTO();
	}

	// /demo3?term=1
	@GetMapping(value = "demo3", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public List<CategoryDTO> demo3(@RequestParam("term") String term) {
		return categoryService.findByKeyword(term);
	}

	@GetMapping(value = "findProductById", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ProductDTO findProductById(@RequestParam("id") int id) {
		return productJPAService.findDTO(id);
	}

	@GetMapping(value = "findProductByKeyword", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public List<ProductDTO> findProductByKeyword(@RequestParam("keyword") String keyword) {
		return productJPAService.findByKeywordDTO(keyword);
	}

	@GetMapping(value = "findProductByPrices", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public List<ProductDTO> findProductByPrices(@RequestParam("min") double min, @RequestParam("max") double max) {
		return productJPAService.findByPricesDTO(min, max);
	}

	@GetMapping(value = "findProductByCategoryId", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public List<ProductDTO> findProductByCategoryId(@RequestParam("categoryId") int categoryId) {
		if (categoryId == -1) {
			return productJPAService.findAllDTO();
		} else {
			return productJPAService.findByCategoryIdDTO(categoryId);
		}
	}

}
