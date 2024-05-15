package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.entities.Comment;
import com.demo.repositories.CommentRepository;

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
	public long countTotalComments() {
		return commentRepository.count();
	}

	@Override
	public List<Comment> findSongsWithPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Comment> pageComments = commentRepository.findAll(pageable);
		List<Comment> comments = pageComments.getContent();
		return comments;
	}

}
