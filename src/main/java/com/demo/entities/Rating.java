package com.demo.entities;
// Generated May 3, 2024, 7:07:39 PM by Hibernate Tools 4.3.6.Final

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * Rating generated by hbm2java
 */
@Entity
@Table(name = "rating", catalog = "bachu_database")
public class Rating implements java.io.Serializable {

	private Integer ratingId;
	private AccountSong accountSong;
	private Integer rating;
	private Date ratingDate;

	public Rating() {
	}

	public Rating(AccountSong accountSong, Integer rating, Date ratingDate) {
		this.accountSong = accountSong;
		this.rating = rating;
		this.ratingDate = ratingDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "rating_id", unique = true, nullable = false)
	public Integer getRatingId() {
		return this.ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_song_id")
	public AccountSong getAccountSong() {
		return this.accountSong;
	}

	public void setAccountSong(AccountSong accountSong) {
		this.accountSong = accountSong;
	}

	@Column(name = "rating")
	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "rating_date", length = 19)
	public Date getRatingDate() {
		return this.ratingDate;
	}

	public void setRatingDate(Date ratingDate) {
		this.ratingDate = ratingDate;
	}

}
