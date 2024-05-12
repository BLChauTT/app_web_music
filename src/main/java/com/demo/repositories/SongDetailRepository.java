package com.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Songdetail;

public interface SongDetailRepository extends JpaRepository<Songdetail, Integer> {
    @Query("from Songdetail where title like %:keyword%")
    public List<Songdetail> findByKeyword(@Param("keyword") String keyword);
    @Query("from Songdetail where title LIKE %:partialTitle%")
    public List<Songdetail> findSongdetailByTitle(@Param("partialTitle") String partialTitle);
    @Query("from Songdetail where year(releaseDate) = :year")
    public List<Songdetail> findByYear(@Param("year") int year);

    @Query("SELECT a FROM Songdetail a")
	public Page<Songdetail> findAllPaged(Pageable pageable);
    //List<Songdetail> findSongdetailByUploader(String uploader);
    Songdetail findById(int id);

    @Query("from Songdetail where fileUrl = :fileUrl and songCoverUrl = :songCoverUrl")
    public Songdetail findByFileUrlAndSongCoverUrl(@Param("fileUrl") String file_url, @Param("songCoverUrl") String songCoverUrl);

    @Query("from Songdetail where fileUrl = :fileUrl")
    public Songdetail findByFileUrl(String fileUrl);
    @Query("from Songdetail where songCoverUrl = :songCoverUrl")
    public Songdetail findBySongCoverUrl(String songCoverUrl);
    @Modifying
    @Query("DELETE FROM Songdetail sd WHERE sd.songDetailId IN (SELECT s.songdetail.songDetailId FROM Song s WHERE s.songId = :songId)")
    public void deleteBySongId(@Param("songId") int songId);
}
