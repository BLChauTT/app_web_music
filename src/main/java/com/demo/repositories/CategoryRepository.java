package com.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT a FROM Category a")
    public Page<Category> findAllPaged(Pageable pageable);
}
