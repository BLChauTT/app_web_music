package com.demo.services;

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
    public Song getSongById(int id) throws Exception {
        return songRepository.getById(id);
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
}
