package com.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "song_singer", catalog = "bachu_database")
public class SongSinger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "song_id", referencedColumnName = "song_id")
    private Song song;

    @ManyToOne
    @JoinColumn(name = "singer_id", referencedColumnName = "singer_id")
    private Singer singer;

    // Constructors, getters, setters

    public SongSinger() {
    }

    public SongSinger(Song song, Singer singer) {
        this.song = song;
        this.singer = singer;
    }

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}


}
