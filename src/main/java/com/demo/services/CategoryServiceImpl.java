package com.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.entities.Category;
import com.demo.repositories.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
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
    public List<Category> findByKeyword(String keyword) {
        return null;
    }

    @Override
    public List<Category> findSongsWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Category> pageCategories = categoryRepository.findAll(pageable);
        List<Category> categories = pageCategories.getContent();
        return categories;
    }
    @Override
    public long countTotalCategories() {
        return categoryRepository.count();
    }
}
