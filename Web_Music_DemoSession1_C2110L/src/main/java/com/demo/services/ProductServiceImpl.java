package com.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.entities.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public Product find() {
		return new Product(1, "Name 1", "thumb1.gif", new Date(), 4.5, 2);
	}

	@Override
	public List<Product> findAll() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(1, "tivi", "thumb1.gif", new Date(), 2, 7));
		products.add(new Product(2, "laptop", "thumb2.gif", new Date(), 2, 5));
		products.add(new Product(3, "computer", "thumb3.gif", new Date(), 4, 3));
		return products;
	}

	@Override
	public Product findById(int id) {
		for (Product product : findAll()) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}

	@Override
	public List<String> searchByKeyword(String keyword) {
		List<String> names = new ArrayList<String>();
		for (Product product : findAll()) {
			if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
				names.add(product.getName());
			}
		}
		return names;
	}

	@Override
	public List<Product> searchByKeyword2(String keyword) {
		List<Product> result = new ArrayList<Product>();
		for (Product product : findAll()) {
			if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
				result.add(product);
			}
		}
		return result;
	}

}
