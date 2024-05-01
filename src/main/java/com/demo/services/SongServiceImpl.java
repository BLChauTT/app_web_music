package com.demo.services;

import com.demo.entities.Song;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService{

    @Autowired
    private SongService songService;

    @Override
    public boolean save(Song song) throws Exception {
        try {
            songService.save(song);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Song getSongById(int id) throws Exception {
        return songService.getSongById(id);
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
        return songService.findAll();
    }
}
