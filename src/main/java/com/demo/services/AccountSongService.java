package com.demo.services;

import java.util.List;

import com.demo.entities.AccountSong;
import com.demo.entities.Comment;
import com.demo.entities.Rating;

public interface AccountSongService {

	public List<AccountSong> findByAccountId(int accountId);
	
	public List<Rating> ratingByAccountId(int accountId);
	
	public List<Comment> commentByAccountId(int accountId);
}
