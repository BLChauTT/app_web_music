package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Album;
import com.demo.repositories.AlbumRepository;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Override
    public boolean save(Album album) {
        try {
            albumRepository.save(album);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public Iterable<Album> findAll() {
        return albumRepository.findAll();
    }
    @Override
    public Album find(int id) {
        for (Album album : albumRepository.findAll()) {
            if (album.getAlbumId() == id) {
                return album;
            }
        }
        return null;
    }
}
