package com.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.CategoryDTO;
import com.demo.entitiesjpa.Category;
import com.demo.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public boolean save(Category category) {
		try {
			categoryRepository.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			categoryRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Category find(int id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public CategoryDTO findDTO(int id) {
		return modelMapper.map(categoryRepository.findById(id).get(), CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> findAllDTO() {
		return modelMapper.map(categoryRepository.findAll(), 
					new TypeToken<List<CategoryDTO>>() {}.getType());
	}

	@Override
	public List<CategoryDTO> findByKeyword(String keyword) {
		return modelMapper.map(categoryRepository.findByKeyword(keyword), 
					new TypeToken<List<CategoryDTO>>() {}.getType());
	}

}
