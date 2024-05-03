package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Song;
import com.demo.entities.Songdetail;

public interface SongDetailRepository extends CrudRepository<Songdetail, Integer> {
    @Query("from Songdetail where title like %:keyword%")
    public List<Songdetail> findByKeyword(@Param("keyword") String keyword);
    @Query("from Songdetail where year(releaseDate) = :year")
    public List<Songdetail> findByYear(@Param("year") int year);
    
}
