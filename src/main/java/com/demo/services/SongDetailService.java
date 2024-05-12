package com.demo.services;

import java.util.List;

import com.demo.entities.Songdetail;

public interface SongDetailService {
    public Iterable<Songdetail> findAll();

  //pagination
  	public List<Songdetail> findSongsWithPagination(int offset, int pageSize);
	public long countTotalSongs();
    public List<Songdetail> findKeywordSong(String keyword);
    public boolean save(Songdetail songdetail);
    public Songdetail find(int id);
    public List<Songdetail> findByKeywordAjax(String keyword);
	public Songdetail findByFileUrlAndSongCoverUrl(String fileUrl, String songCoverUrl);
    public Songdetail findByFileUrl(String fileUrl);
    public Songdetail findBySongCoverUrl(String songCoverUrl);
}
