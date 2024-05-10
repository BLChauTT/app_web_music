package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.Favorite;
import com.demo.entities.Rating;
import com.demo.entities.SongSinger;

@Repository
public interface SongSingerRepository extends JpaRepository<SongSinger, Integer>{

	@Transactional
    @Modifying
    @Query("DELETE FROM SongSinger ss WHERE ss.song.id = :songId")
    void deleteBySongId(@Param("songId") int songId);
}
