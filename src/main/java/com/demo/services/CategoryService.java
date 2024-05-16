package com.demo.services;

import java.util.List;

import com.demo.entities.Category;

public interface CategoryService {
    public Iterable<Category> findAll();
    public boolean save(Category category);
    public boolean delete(int id);
    public Category find(int id);
    public long count();
    public List<Category> findByKeyword(String keyword);
    public List<Category> findSongsWithPagination(int offset, int pageSize);
    public long countTotalCategories();
}
