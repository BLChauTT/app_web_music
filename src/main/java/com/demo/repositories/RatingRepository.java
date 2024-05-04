package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Notification;
import com.demo.entities.Rating;

public interface RatingRepository extends CrudRepository<Rating, Integer> {

	@Query("from Rating where accountSong.accountSongId = :accountSongId")
    public List<Rating> findByAccountId(@Param("accountSongId") int accountSongId);
}
