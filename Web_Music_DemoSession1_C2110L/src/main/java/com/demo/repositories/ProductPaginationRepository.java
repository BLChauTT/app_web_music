package com.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.entitiesjpa.Product;

@Repository
public interface ProductPaginationRepository 
	extends PagingAndSortingRepository<Product, Integer> {

}
