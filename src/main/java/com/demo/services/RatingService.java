package com.demo.services;

import java.util.List;

import com.demo.entities.Rating;

public interface RatingService {
    public List<Rating> findAll();
	public List<Rating> ratingByAccountId(int accountId);
	public boolean delete(int id);
	public List<Rating> findSongsWithPagination(int offset, int pageSize);
	public long countTotalRatngs();
}
