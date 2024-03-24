package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.entities.City;
import com.demo.entities.Country;

@Service
public class CountryServiceImpl implements CountryService {

	private List<Country> countries;

	public CountryServiceImpl() {
		countries = new ArrayList<Country>();

		Country country1 = new Country(1, "Country 1");
		country1.getCities().add(new City(1, "City 1"));
		country1.getCities().add(new City(2, "City 2"));
		countries.add(country1);

		Country country2 = new Country(2, "Country 2");
		country2.getCities().add(new City(3, "City 3"));
		country2.getCities().add(new City(4, "City 4"));
		country2.getCities().add(new City(5, "City 5"));
		countries.add(country2);

	}

	@Override
	public List<Country> findAll() {
		return countries;
	}

	@Override
	public Country findById(int id) {
		for (Country country : countries) {
			if (country.getId() == id) {
				return country;
			}
		}
		return null;
	}

}
