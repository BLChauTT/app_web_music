package com.demo.services;

import com.demo.entities.Singer;
import com.demo.entities.Song;

import java.util.List;

public interface SingerService {
    public boolean save(Singer singer) throws Exception;
    public Singer findSingerById(int id);
    public Singer findSingerByKeyword(String keyword);
    public Singer updateSinger(int id, Singer singer);
    public void deleteSinger(int id);
    public List<Singer> searchByKeyword(String keyword);
    public Iterable<Singer> findAll();
}
