package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
