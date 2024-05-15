package com.demo.repositories;

import com.demo.entities.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Category;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT a FROM Category a")
    public Page<Category> findAllPaged(Pageable pageable);
}
