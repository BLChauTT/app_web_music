package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.entities.Language;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Override
	public List<Language> findAll() {
		List<Language> languages = new ArrayList<Language>();
		languages.add(new Language(1, "Language 1"));
		languages.add(new Language(2, "Language 2"));
		languages.add(new Language(3, "Language 3"));
		languages.add(new Language(4, "Language 4"));
		languages.add(new Language(5, "Language 5"));
		return languages;
	}

	@Override
	public Language find() {
		return new Language(1, "Language 6");
	}

}
