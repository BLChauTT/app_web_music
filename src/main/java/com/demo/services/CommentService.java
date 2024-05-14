package com.demo.services;

import java.util.List;

import com.demo.entities.Comment;

public interface CommentService {
    public List<Comment> findAll();
	public List<Comment> commentByAccountId(int accountId);
	public boolean delete(int id);
	public List<Comment> findSongsWithPagination(int offset, int pageSize);
}
