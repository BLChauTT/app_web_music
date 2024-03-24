package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.repositories.LanguageRepository;

@Service("languageJPAService")
public class LanguageJPAServiceImpl implements LanguageJPAService {

	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public Iterable<com.demo.entitiesjpa.Language> findAll() {
		return languageRepository.findAll();
	}

}
