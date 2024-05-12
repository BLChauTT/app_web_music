package com.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entities.Singer;

public interface SingerRepository extends JpaRepository<Singer, Integer> {
    @Query("SELECT s FROM Singer s")
    public Page<Singer> findAllPaged(Pageable pageable);
}
