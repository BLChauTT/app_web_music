package com.demo.services;

import java.util.List;

import com.demo.entities.Songdetail;

public interface SongDetailService {
    public Iterable<Songdetail> findAll();
    public List<Songdetail> findKeywordSong(String keyword);
    public boolean save(Songdetail songdetail);
    public Songdetail find(int id);
    public List<Songdetail> findByKeywordAjax(String keyword);
}
