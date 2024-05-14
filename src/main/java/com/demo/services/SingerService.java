package com.demo.services;

import java.util.List;
import java.util.Set;

import com.demo.entities.Singer;

public interface SingerService {
    public boolean save(Singer singer) throws Exception;
    public Singer findSingerById(int id);
    public List<Singer> findByNameContainingIgnoreCase(String keyword);
    public long countTotalSingers();
    public Singer findSingerByKeyword(String keyword);
    public Singer updateSinger(int id, Singer singer);
    public void deleteSinger(int id);
    public List<Singer> searchByKeyword(String keyword);
    public Iterable<Singer> findAll();
    public String findSingerCoverUrlBySingerId(int songId);
    public Set<Singer> findSingersBySongId(int songId);
    public List<Singer> findSingersWithPagination(int offset, int pageSize);
}
