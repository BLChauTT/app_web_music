package com.demo.entities;
// Generated May 8, 2024, 11:11:08 PM by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


/**
 * Category generated by hbm2java
 */
@Entity
@Table(name = "category", catalog = "bachu_database", uniqueConstraints = @UniqueConstraint(columnNames = "category_name"))
public class Category implements java.io.Serializable {

	private Integer categoryId;
	private String categoryName;
	private Set<Songdetail> songdetails = new HashSet<>(0);

	public Category() {
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category(String categoryName, Set<Songdetail> songdetails) {
		this.categoryName = categoryName;
		this.songdetails = songdetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "category_id", unique = true, nullable = false)
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_name", unique = true, nullable = false, length = 100)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Songdetail> getSongdetails() {
		return this.songdetails;
	}

	public void setSongdetails(Set<Songdetail> songdetails) {
		this.songdetails = songdetails;
	}

}
