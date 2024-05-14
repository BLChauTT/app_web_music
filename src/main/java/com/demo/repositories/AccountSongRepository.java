package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.AccountSong;

@Repository
public interface AccountSongRepository extends JpaRepository<AccountSong, Integer> {
	@Query("from AccountSong where account.accountId = :accountId")
    public List<AccountSong> findByAccountId(@Param("accountId") int accountId);
	@Query("from AccountSong where song.songId = :songId")
    public List<AccountSong> findBySongId(@Param("songId") int songId);
	//search bai hat
	@Query("from AccountSong where song.songdetail.title LIKE %:partialTitle%")
    public List<AccountSong> findBySongDetail(@Param("partialTitle") String partialTitle);
	 @Query("DELETE FROM AccountSong a WHERE a.song.songId = :songId")
	 public void deleteBySongId(@Param("songId") int songId);
}
