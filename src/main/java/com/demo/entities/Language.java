package com.demo.entities;

public class Language {

	private int id;
	private String name;

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

	public Language(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Language() {
		super();
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", name=" + name + "]";
	}

}
