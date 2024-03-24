package com.demo.services;

import java.util.List;

import com.demo.dtos.CategoryDTO;
import com.demo.entitiesjpa.Category;

public interface CategoryService {

	public Iterable<Category> findAll();
	
	public boolean save(Category category);
	
	public boolean delete(int id);
	
	public Category find(int id);
	
	public long count();
	
	public CategoryDTO findDTO(int id);
	
	public List<CategoryDTO> findAllDTO();
	
	public List<CategoryDTO> findByKeyword(String keyword);
	
}

