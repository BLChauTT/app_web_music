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
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public List<Comment> commentByAccountId(int accountId) {
		return commentRepository.findByAccountId(accountId);
	}

	@Override
	public boolean delete(int id) {
		try {
			commentRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Comment> findSongsWithPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Comment> pageSongs = commentRepository.findAll(pageable);
		List<Comment> songs = pageSongs.getContent();
		return songs;
	}
	
}
