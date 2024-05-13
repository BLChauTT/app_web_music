package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.AccountSong;
import com.demo.entities.Comment;
import com.demo.entities.Rating;
import com.demo.repositories.AccountSongRepository;
import com.demo.repositories.CommentRepository;
import com.demo.repositories.FavoriteRepository;
import com.demo.repositories.RatingRepository;
import com.demo.repositories.SongDetailRepository;
import com.demo.repositories.SongRepository;
import com.demo.repositories.SongSingerRepository;

@Service
public class RatingServiceImpl implements RatingService {
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public List<Rating> findAll() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> ratingByAccountId(int accountId) {
		return ratingRepository.findByAccountId(accountId);
	}

	@Override
	public boolean delete(int id) {
		try {
			ratingRepository.deleteById(id);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public List<Rating> findSongsWithPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Rating> pageRatings = ratingRepository.findAll(pageable);
		List<Rating> ratings = pageRatings.getContent();
		return ratings;
	}
	
}
