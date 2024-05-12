package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Override
    public List<Album> findSongsWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Album> pageAlbums = albumRepository.findAll(pageable);
        List<Album> albums = pageAlbums.getContent();
        return albums;
    }
}
