package com.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Singer;

public interface SingerRepository extends JpaRepository<Singer, Integer> {
    @Query("SELECT s FROM Singer s")
    public Page<Singer> findAllPaged(Pageable pageable);
    @Query("SELECT s FROM Singer s WHERE LOWER(s.singerName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Singer> findByNameContainingIgnoreCase(@Param("name") String name);
}
