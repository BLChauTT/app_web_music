package com.demo.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Employee {

	private String username;
	private String password;
	private String description;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dob;

	private boolean status;
	private String gender;
	private int cert;
	private int[] languages;
	private int role;
	private int id;
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int[] getLanguages() {
		return languages;
	}

	public void setLanguages(int[] languages) {
		this.languages = languages;
	}

	public int getCert() {
		return cert;
	}

	public void setCert(int cert) {
		this.cert = cert;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
