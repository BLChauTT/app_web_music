package com.demo.services;

import java.util.List;

import com.demo.entities.Songdetail;

public interface SongDetailService {
    public Iterable<Songdetail> findAll();
    
  //pagination
  	public List<Songdetail> findSongsWithPagination(int offset, int pageSize);
    
    public List<Songdetail> findKeywordSong(String keyword);

	public long countTotalSongs();
}
