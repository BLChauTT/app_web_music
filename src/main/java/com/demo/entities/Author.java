package com.demo.entities;
// Generated May 8, 2024, 3:01:13 PM by Hibernate Tools 4.3.6.Final

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

/**
 * Author generated by hbm2java
 */
@Entity
@Table(name = "author", catalog = "bachu_database", uniqueConstraints = @UniqueConstraint(columnNames = "author_name"))
public class Author implements java.io.Serializable {

	private Integer authorId;
	private String authorName;
	private Set<Song> songs = new HashSet<Song>(0);

	public Author() {
	}

	public Author(String authorName) {
		this.authorName = authorName;
	}

	public Author(String authorName, Set<Song> songs) {
		this.authorName = authorName;
		this.songs = songs;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "author_id", unique = true, nullable = false)
	public Integer getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Column(name = "author_name", unique = true, nullable = false)
	public String getAuthorName() {
		return this.authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	public Set<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

}
