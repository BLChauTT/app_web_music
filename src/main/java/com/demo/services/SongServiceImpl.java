package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Song;
import com.demo.repositories.SongRepository;

@Service
public class SongServiceImpl implements SongService{

    @Autowired
    public SongRepository songRepository;

    @Override
    public boolean save(Song song) throws Exception {
        try {
            songRepository.save(song);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Song findSongById(int id) {
        for (Song song : findAll()) {
            if (song.getSongId() == id) {
                return song;
            }
        }
        return null;
    }
    @Override
    public Song updateSong(int id, Song song) {
        return null;
    }
    @Override
    public void deleteSong(int id) {

    }
    @Override
    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> getSongsByAuthor(int authorId) {
        return songRepository.findByAuthorId(authorId);
    }

    @Override
    public List<Song> findSongsBySingerId(int singerId) {
        return songRepository.findSongsBySingerId(singerId);
    }
	@Override
	public List<Song> getSongsByAccount(int accountId) {
		return songRepository.findByAccountId(accountId);
	}

}
