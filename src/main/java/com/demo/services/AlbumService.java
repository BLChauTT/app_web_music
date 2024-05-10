package com.demo.services;

import com.demo.entities.Album;

public interface AlbumService {
    public boolean save(Album album);
    public Iterable<Album> findAll();
    public Album find(int id);
}
