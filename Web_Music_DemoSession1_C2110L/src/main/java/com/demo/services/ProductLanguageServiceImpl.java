package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entitiesjpa.Language;
import com.demo.entitiesjpa.ProductLanguage;
import com.demo.repositories.LanguageRepository;
import com.demo.repositories.ProductLanguageRepository;

@Service("productLanguageService")
public class ProductLanguageServiceImpl implements ProductLanguageService {

	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private ProductLanguageRepository productLanguageRepository;
	
	@Override
	public ProductLanguage find(String locale, int productId) {
		String[] result = locale.split("_");
		Language language = languageRepository.find(result[0], result[1]);
		return productLanguageRepository.find(language.getId(), productId);
	}

}
