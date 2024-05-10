package com.demo.services;

import com.demo.entities.Album;
import com.demo.entities.Author;

public interface AlbumService {
    public boolean save(Album album);
    public Album find(int id);
    public Iterable<Album> findAll();
}
