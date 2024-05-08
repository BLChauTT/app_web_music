package com.demo.entities;
// Generated May 8, 2024, 11:11:08 PM by Hibernate Tools 4.3.6.Final

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * AccountSong generated by hbm2java
 */
@Entity
@Table(name = "account_song", catalog = "bachu_database")
public class AccountSong implements java.io.Serializable {

	private Integer accountSongId;
	private Account account;
	private Song song;
	private Date postDate;
	private Set<Favorite> favorites = new HashSet<>(0);
	private Set<Comment> comments = new HashSet<>(0);
	private Set<Rating> ratings = new HashSet<>(0);

	public AccountSong() {
	}

	public AccountSong(Account account, Song song, Date postDate, Set<Favorite> favorites, Set<Comment> comments,
			Set<Rating> ratings) {
		this.account = account;
		this.song = song;
		this.postDate = postDate;
		this.favorites = favorites;
		this.comments = comments;
		this.ratings = ratings;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "account_song_id", unique = true, nullable = false)
	public Integer getAccountSongId() {
		return this.accountSongId;
	}

	public void setAccountSongId(Integer accountSongId) {
		this.accountSongId = accountSongId;
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
	@JoinColumn(name = "song_id")
	public Song getSong() {
		return this.song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "post_date", length = 10)
	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountSong")
	public Set<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountSong")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountSong")
	public Set<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

}
