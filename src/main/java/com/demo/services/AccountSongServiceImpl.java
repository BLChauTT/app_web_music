package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Override
	public boolean save(AccountSong accountSong) {
		try {
			accountSongRepository.save(accountSong);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<AccountSong> findBySongId(int songId) {
		return accountSongRepository.findBySongId(songId);
	}

	@Override
	public List<AccountSong> findAll() {
		return accountSongRepository.findAll();
	}

	@Override
	public List<AccountSong> findByTitle(String title) {
		return accountSongRepository.findBySongDetail(title);
	}

	@Override
	public List<AccountSong> findSongsWithPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<AccountSong> pageSongs = accountSongRepository.findAll(pageable);
		List<AccountSong> songs = pageSongs.getContent();
		return songs;
	}
}
