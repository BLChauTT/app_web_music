package com.demo.services;

import java.util.List;

import com.demo.entities.Language;

public interface LanguageService {

	public List<Language> findAll();
	
	public Language find();
	
}
