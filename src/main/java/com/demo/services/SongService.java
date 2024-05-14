package com.demo.services;

import java.util.List;

import com.demo.entities.Song;

public interface SongService {
    public boolean save(Song song) throws Exception;
    public Song findSongById(int id);
    public List<Song> findByTitleContainingIgnoreCase(String keyword);
    public Song updateSong(int id, Song song);
    public long countTotalSongs();
    public boolean deleteSong(int id);
    public Iterable<Song> findAll();
    public List<Song> getSongsByAuthor(int authorId);
    public List<Song> findAllList();
    public List<Song> findByTitle(String title);
    public List<Song> findSongsWithPagination(int offset, int pageSize);
    public String findFileUrlBySongId(int songId);
    public String findSongCoverUrlBySongId(int songId);
}
