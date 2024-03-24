package com.demo.services;

import com.demo.entitiesjpa.ProductLanguage;

public interface ProductLanguageService {

	public ProductLanguage find(String locale, int productId);
	
}
