package com.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
