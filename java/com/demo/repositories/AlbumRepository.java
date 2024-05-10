package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Album;
import com.demo.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    @Query("from Category where categoryName like %:keyword%")
    public List<Category> findByKeyword(@Param("keyword") String keyword);
}
