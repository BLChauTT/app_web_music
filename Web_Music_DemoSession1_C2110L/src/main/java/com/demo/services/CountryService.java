package com.demo.services;

import java.util.List;

import com.demo.entities.Country;

public interface CountryService {

	public List<Country> findAll();
	
	public Country findById(int id);
	
}
