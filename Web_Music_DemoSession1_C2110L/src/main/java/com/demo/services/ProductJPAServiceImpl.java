package com.demo.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.dtos.CategoryDTO;
import com.demo.dtos.ProductDTO;
import com.demo.entitiesjpa.Product;
import com.demo.repositories.ProductPaginationRepository;
import com.demo.repositories.ProductRepository;

@Service
public class ProductJPAServiceImpl implements ProductJPAService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductPaginationRepository productPaginationRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findByKeyword(String keyword) {
		return productRepository.findByKeyword(keyword);
	}

	@Override
	public List<Product> findByPrices(double min, double max) {
		return productRepository.findByPrices(min, max);
	}

	@Override
	public List<Product> findByDates(String start, String end) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date from = simpleDateFormat.parse(start);
			Date to = simpleDateFormat.parse(end);
			return productRepository.findByDates(from, to);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> findByCategoryId(int categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	@Override
	public List<Product> limit2(int start, int n) {
		return productRepository.limit2(start, n);
	}

	@Override
	public long sum1() {
		return productRepository.sum1();
	}

	@Override
	public boolean save(Product product) {
		try {
			productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Product find(int id) {
		return productRepository.findById(id).get();
	}

	@Override
	public ProductDTO findDTO(int id) {
		return modelMapper.map(productRepository.findById(id).get(), ProductDTO.class);
	}

	@Override
	public List<ProductDTO> findAllDTO() {
		return modelMapper.map(productRepository.findAll(), new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> findByPricesDTO(double min, double max) {
		return modelMapper.map(productRepository.findByPrices(min, max), 
				new TypeToken<List<ProductDTO>>() {}.getType());
	}

	@Override
	public List<ProductDTO> findByCategoryIdDTO(int categoryId) {
		return modelMapper.map(productRepository.findByCategoryId(categoryId), 
				new TypeToken<List<ProductDTO>>() {}.getType());
	}
	
	@Override
	public List<ProductDTO> findByKeywordDTO(String keyword) {
		return modelMapper.map(productRepository.findByKeyword(keyword), 
				new TypeToken<List<ProductDTO>>() {}.getType());
	}

	@Override
	public Page<Product> findAllPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return productPaginationRepository.findAll(pageable);
	}

}
