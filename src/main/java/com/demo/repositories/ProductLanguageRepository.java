package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entitiesjpa.ProductLanguage;

@Repository
public interface ProductLanguageRepository extends CrudRepository<ProductLanguage, Integer> {

	@Query("from ProductLanguage where language.id = :languageId and product.id = :productId")
	public ProductLanguage find(@Param("languageId") int languageId, @Param("productId") int productId);

}
