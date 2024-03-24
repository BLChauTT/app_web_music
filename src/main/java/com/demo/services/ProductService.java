package com.demo.services;

import java.util.List;

import com.demo.entities.Product;

public interface ProductService {

	public Product find();
	
	public List<Product> findAll();
	
	public Product findById(int id);
	
	public List<String> searchByKeyword(String keyword);
	
	public List<Product> searchByKeyword2(String keyword);
	
}
