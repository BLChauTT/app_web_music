package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

	@Query("from Comment where accountSong.accountSongId = :accountSongId")
    public List<Comment> findByAccountId(@Param("accountSongId") int accountSongId);
}
