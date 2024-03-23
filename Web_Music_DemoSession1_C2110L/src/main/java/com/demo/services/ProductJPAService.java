package com.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.dtos.ProductDTO;
import com.demo.entitiesjpa.Product;

public interface ProductJPAService {

	public Iterable<Product> findAll();
	
	public List<Product> findByKeyword(String keyword);
	
	public List<Product> findByPrices(double min, double max);
	
	public List<Product> findByDates(String start, String end);
	
	public List<Product> findByCategoryId(int categoryId);
	
	public List<Product> limit2(int start, int n);
	
	public long sum1();
	
	public boolean save(Product product);
	
	public Product find(int id);
	
	public ProductDTO findDTO(int id);
	
	public List<ProductDTO> findAllDTO();
	
	public List<ProductDTO> findByPricesDTO(double min, double max);
	
	public List<ProductDTO> findByCategoryIdDTO(int categoryId);
	
	public List<ProductDTO> findByKeywordDTO(String keyword);
	
	public Page<Product> findAllPagination(int pageNo, int pageSize);
	
}
