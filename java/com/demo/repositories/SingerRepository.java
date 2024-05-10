package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Singer;

public interface SingerRepository extends JpaRepository<Singer, Integer> {
}
