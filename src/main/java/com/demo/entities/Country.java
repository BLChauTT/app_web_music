package com.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class Country {

	private int id;
	private String name;
	private List<City> cities = new ArrayList<City>();

	public Country(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Country() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}
