package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Song;

public interface SongRepository extends JpaRepository<Song, Integer> {
    @Query("from Song where author.authorId = :authorId")
    public List<Song> findByAuthorId(@Param("authorId") int authorId);
    @Query("SELECT s FROM Song s JOIN s.singers sg WHERE sg.singerId = :singerId")
    List<Song> findSongsBySingerId(@Param("singerId") int singerId);
}
