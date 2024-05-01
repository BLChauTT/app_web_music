package com.demo.repositories;

import com.demo.entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerRepository extends JpaRepository<Singer, Integer> {
}
