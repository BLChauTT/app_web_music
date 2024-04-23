package com.demo.entities;
// Generated Apr 23, 2024, 10:45:04 AM by Hibernate Tools 4.3.6.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

/**
 * Song generated by hbm2java
 */
@Entity
@Table(name = "song", catalog = "music_app")
public class Song implements java.io.Serializable {

	private Integer songId;
	private Account account;
	private Author author;
	private Songdetail songdetail;
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<AccountSong> accountSongs = new HashSet<AccountSong>(0);
	private Set<Favorite> favorites = new HashSet<Favorite>(0);
	private Set<Rating> ratings = new HashSet<Rating>(0);
	private Set<Category> categories = new HashSet<Category>(0);
	private Set<Singer> singers = new HashSet<Singer>(0);

	public Song() {
	}

	public Song(Account account, Author author, Songdetail songdetail, Set<Comment> comments,
			Set<AccountSong> accountSongs, Set<Favorite> favorites, Set<Rating> ratings, Set<Category> categories,
			Set<Singer> singers) {
		this.account = account;
		this.author = author;
		this.songdetail = songdetail;
		this.comments = comments;
		this.accountSongs = accountSongs;
		this.favorites = favorites;
		this.ratings = ratings;
		this.categories = categories;
		this.singers = singers;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "song_id", unique = true, nullable = false)
	public Integer getSongId() {
		return this.songId;
	}

	public void setSongId(Integer songId) {
		this.songId = songId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_detail_id")
	public Songdetail getSongdetail() {
		return this.songdetail;
	}

	public void setSongdetail(Songdetail songdetail) {
		this.songdetail = songdetail;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "song")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "song")
	public Set<AccountSong> getAccountSongs() {
		return this.accountSongs;
	}

	public void setAccountSongs(Set<AccountSong> accountSongs) {
		this.accountSongs = accountSongs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "song")
	public Set<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "song")
	public Set<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "songcategory", catalog = "music_app", joinColumns = {
			@JoinColumn(name = "song_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "category_id", nullable = false, updatable = false) })
	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "song_singer", catalog = "music_app", joinColumns = {
			@JoinColumn(name = "song_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "singer_id", nullable = false, updatable = false) })
	public Set<Singer> getSingers() {
		return this.singers;
	}

	public void setSingers(Set<Singer> singers) {
		this.singers = singers;
	}

}