package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Songdetail;
import com.demo.repositories.SongDetailRepository;

@Service
public class SongDetailServiceImpl implements SongDetailService{

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




}
