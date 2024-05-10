package com.demo.services;

import com.demo.entities.Album;
import com.demo.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImlp implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    @Override
    public boolean save(Album album) {
        try {
            albumRepository.save(album);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Album find(int id) {
        for (Album album: albumRepository.findAll()) {
            if (album.getAlbumId() == id) {
                return album;
            }
        }
        return null;
    }

    @Override
    public Iterable<Album> findAll() {
        return albumRepository.findAll();
    }
}
