package com.demo.entities;

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

@Entity
@Table(name = "song", catalog = "bachu_database")
public class Song implements java.io.Serializable {

	private Integer songId;
	private Category category;
	private Album album;
	private Author author;
	private Songdetail songdetail;
	private Set<AccountSong> accountSongs = new HashSet<>(0);
	private Set<Singer> singers = new HashSet<>(0);

	public Song() {
	}

	public Song(Set<Singer> singers) {
		this.singers = singers;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "song_singer",
			joinColumns = { @JoinColumn(name = "song_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "singer_id", nullable = false, updatable = false) })
	public Set<Singer> getSingers() {
		return this.singers;
	}
	public void setSingers(Set<Singer> singers) {
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
	@JoinColumn(name = "album_id")
	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
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
	public Set<AccountSong> getAccountSongs() {
		return this.accountSongs;
	}

	public void setAccountSongs(Set<AccountSong> accountSongs) {
		this.accountSongs = accountSongs;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
