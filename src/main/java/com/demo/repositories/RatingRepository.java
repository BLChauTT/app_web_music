package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	@Query("from Rating where accountSong.account.accountId = :accountId")
    public List<Rating> findByAccountId(@Param("accountId") int accountId);

	@Modifying
	@Query("DELETE FROM Rating where accountSong.accountSongId = :accountSongId")
	 public void deleteByAccountSong_AccountSongId(@Param("accountSongId") int accountSongId);
}
