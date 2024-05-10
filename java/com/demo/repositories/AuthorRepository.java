package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	@Query("from Author where authorName = :authorName")
	public Author findAuthorByKeyword(@Param("authorName") String authorName);
}
