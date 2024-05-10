package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("from Comment where accountSong.account.accountId = :accountId")
    public List<Comment> findByAccountId(@Param("accountId") int accountId);
	
	@Modifying
	@Query("DELETE FROM Comment c WHERE c.accountSong.accountSongId = :accountSongId")
	public void deleteByAccountSong_AccountSongId(@Param("accountSongId") int accountSongId);

}
