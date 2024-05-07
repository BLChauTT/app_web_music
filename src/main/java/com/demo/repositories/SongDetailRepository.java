package com.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Account;
import com.demo.entities.Songdetail;

public interface SongDetailRepository extends JpaRepository<Songdetail, Integer> {
    @Query("from Songdetail where title like %:keyword%")
    public List<Songdetail> findByKeyword(@Param("keyword") String keyword);
    @Query("from Songdetail where year(releaseDate) = :year")
    public List<Songdetail> findByYear(@Param("year") int year);
    
    @Query("SELECT a FROM Songdetail a")
	public Page<Songdetail> findAllPaged(Pageable pageable);
    //List<Songdetail> findSongdetailByUploader(String uploader);
    Songdetail findById(int id);

}
