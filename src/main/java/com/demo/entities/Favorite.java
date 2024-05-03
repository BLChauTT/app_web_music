package com.demo.entities;
// Generated May 3, 2024, 8:30:25 PM by Hibernate Tools 4.3.6.Final

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Favorite generated by hbm2java
 */
@Entity
@Table(name = "favorite", catalog = "bachu_database")
public class Favorite implements java.io.Serializable {

	private Integer favoriteId;
	private AccountSong accountSong;

	public Favorite() {
	}

	public Favorite(AccountSong accountSong) {
		this.accountSong = accountSong;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "favorite_id", unique = true, nullable = false)
	public Integer getFavoriteId() {
		return this.favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_song_id")
	public AccountSong getAccountSong() {
		return this.accountSong;
	}

	public void setAccountSong(AccountSong accountSong) {
		this.accountSong = accountSong;
	}

}
