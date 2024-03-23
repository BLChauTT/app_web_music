package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entitiesjpa.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	@Query("from Category where name like %:keyword%")
	public List<Category> findByKeyword(@Param("keyword") String keyword);
	
}
