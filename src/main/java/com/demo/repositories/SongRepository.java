package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Song;

public interface SongRepository extends JpaRepository<Song, Integer> {
    @Query("from Song where account.accountId = :accountId")
    public List<Song> findByAccountId(@Param("accountId") int categoryId);
}
