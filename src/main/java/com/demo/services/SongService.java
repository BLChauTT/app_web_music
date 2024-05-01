package com.demo.services;

import com.demo.entities.Song;

public interface SongService {
    public boolean save(Song song) throws Exception;
    Song getSongById(int id) throws Exception;
    Song updateSong(int id, Song song);
    void deleteSong(int id);
    public Iterable<Song> findAll();
}
