package com.demo.repositories;

import java.util.List;

import com.demo.entities.Account;
import com.demo.entities.AccountSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Favorite;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
	@Query("from Favorite where accountSong.account.accountId = :accountId")
    public List<Favorite> findByAccountId(@Param("accountId") int accountId);
	@Modifying
	@Query("DELETE FROM Favorite where accountSong.accountSongId = :accountSongId")
	 public void deleteByAccountSong_AccountSongId(@Param("accountSongId") int accountSongId);
	Favorite findByAccountSong(AccountSong accountSong);
	List<Favorite> findByAccountSongAccount(Account account);

}
