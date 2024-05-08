package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.AccountSong;

@Repository
public interface AccountSongRepository extends CrudRepository<AccountSong, Integer> {
	@Query("from AccountSong where account.accountId = :accountId")
    public List<AccountSong> findByAccountId(@Param("accountId") int accountId);


}
