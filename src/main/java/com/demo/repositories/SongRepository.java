package com.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Song;

public interface SongRepository extends JpaRepository<Song, Integer> {
    @Query("from Song where author.authorId = :authorId")
    public List<Song> findByAuthorId(@Param("authorId") int authorId);
    @Query("from Song where category.categoryId = :categoryId")
    public List<Song> findByCategoryId(@Param("categoryId") int categoryId);
    @Query("SELECT s FROM Song s WHERE LOWER(s.songdetail.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Song> findByTitleContainingIgnoreCase(String title);
    @Query("from Song where songdetail.title LIKE %:partialTitle%")
    public List<Song> findBySongDetail(@Param("partialTitle") String partialTitle);
    @Query("SELECT s FROM Song s")
    public Page<Song> findAllPaged(Pageable pageable);
    @Query("SELECT s FROM Song s")
    List<Song> findAudioByUploader(String uploader);
    @Query("SELECT sd.fileUrl FROM Song s JOIN s.songdetail sd WHERE s.songId = :songId")
    public String findFileUrlBySongId(@Param("songId") int songId);
    @Query("SELECT sd.songCoverUrl FROM Song s JOIN s.songdetail sd WHERE s.songId = :songId")
    public String findSongCoverUrlBySongId(@Param("songId") int songId);
    @Query("SELECT s FROM Song s JOIN s.singers sg WHERE sg.singerId = :singerId")
    List<Song> findSongsBySingerId(@Param("singerId") int singerId);
    @Query("SELECT s FROM Song s WHERE s.album.albumId = :albumId")
    List<Song> findSongsByAlbumId(@Param("albumId") int albumId);
    @Query("SELECT s FROM Song s WHERE s.category.categoryId = :categoryId")
    List<Song> findSongsByCategoryId(@Param("categoryId") int albumId);

}
