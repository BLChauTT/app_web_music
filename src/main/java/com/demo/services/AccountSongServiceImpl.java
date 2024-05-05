package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.AccountSong;
import com.demo.entities.Comment;
import com.demo.entities.Rating;
import com.demo.repositories.AccountSongRepository;
import com.demo.repositories.CommentRepository;
import com.demo.repositories.RatingRepository;

@Service
public class AccountSongServiceImpl implements AccountSongService {

	@Autowired
	private AccountSongRepository accountSongRepository;

	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<AccountSong> findByAccountId(int accountId) {
		return accountSongRepository.findByAccountId(accountId);
	}
	

	@Override
	public List<Rating> ratingByAccountId(int accountId) {
		
		return ratingRepository.findByAccountId(accountId);
	}


	@Override
	public List<Comment> commentByAccountId(int accountId) {
		return commentRepository.findByAccountId(accountId);
	}


}
