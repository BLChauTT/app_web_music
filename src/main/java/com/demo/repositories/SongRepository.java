package com.demo.repositories;

import com.demo.entities.Songdetail;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Integer> {
    @Query("from Song where account.accountId = :accountId")
    public List<Song> findByAccountId(@Param("accountId") int categoryId);
}
