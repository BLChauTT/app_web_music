package com.demo.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Product {

	private int id;
	private String name;
	private String photo;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date created;
	
	private double price;
	private int quantity;

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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product(int id, String name, String photo, Date created, double price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.created = created;
		this.price = price;
		this.quantity = quantity;
	}

	public Product() {
		super();
	}

}
