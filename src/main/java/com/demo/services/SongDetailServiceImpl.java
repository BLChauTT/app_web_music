package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.entities.Account;
import com.demo.entities.Songdetail;
import com.demo.repositories.SongDetailRepository;

@Service
public class SongDetailServiceImpl implements SongDetailService {

	@Autowired
	private SongDetailRepository songDetailRepository;

	@Override
	public Iterable<Songdetail> findAll() {
		return songDetailRepository.findAll();
	}

	@Override
	public List<Songdetail> findKeywordSong(String keyword) {
		return songDetailRepository.findByKeyword(keyword);
	}

	// Phân Trang
	@Override
	public List<Songdetail> findSongsWithPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Songdetail> pageSongs = songDetailRepository.findAll(pageable);
		List<Songdetail> songs = pageSongs.getContent();
		return songs;
	}

	@Override
	public long countTotalSongs() {
		// TODO Auto-generated method stub
		return songDetailRepository.count();
	}

}
