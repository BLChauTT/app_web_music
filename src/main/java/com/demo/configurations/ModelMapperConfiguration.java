package com.demo.configurations;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.dtos.CategoryDTO;
import com.demo.dtos.ProductDTO;
import com.demo.entitiesjpa.Category;
import com.demo.entitiesjpa.Product;

@Configuration
public class ModelMapperConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		mapper.addMappings(new PropertyMap<Category, CategoryDTO>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setName(source.getName());
			}
		});

		mapper.addMappings(new PropertyMap<Product, ProductDTO>() {
			@Override
			protected void configure() {
				map().setCategoryId(source.getCategory().getId());
				map().setCategoryName(source.getCategory().getName());
				map().setCreated(source.getCreated());
				map().setDescription(source.getDescription());
				map().setId(source.getId());
				map().setName(source.getName());
				map().setPhoto(source.getPhoto());
				map().setPrice(source.getPrice());
				map().setQuantity(source.getQuantity());
				map().setStatus(source.isStatus());
			}
		});

		return mapper;
	}

}
