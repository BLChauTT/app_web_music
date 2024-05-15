package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.entities.Rating;
import com.demo.repositories.RatingRepository;

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
	
	@Override
	public long countTotalRatngs() {
		return ratingRepository.count();
	}

}
