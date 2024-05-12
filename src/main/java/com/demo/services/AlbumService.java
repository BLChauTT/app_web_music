package com.demo.services;

import java.util.List;

import com.demo.entities.Album;

public interface AlbumService {
    public boolean save(Album album);
    public Iterable<Album> findAll();
    public Album find(int id);
    public List<Album> findSongsWithPagination(int offset, int pageSize);
}
