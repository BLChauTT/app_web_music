package com.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Userprofile;

@Repository
public interface UserProfileRepository extends CrudRepository<Userprofile, Integer> {

	@Query("from Userprofile where account.accountId = :accountId")
	public Userprofile findByAccountId(@Param("accountId") int accountId);

	@Query("from Userprofile where account.accountId = :accountId")
	public Optional<Userprofile> updateFindByAccountId(@Param("accountId") int accountId);

	@Query("SELECT up.avatarUrl FROM Userprofile up WHERE up.account.accountId = :accountId")
	String findAvatarUrlByAccountId(@Param("accountId") int accountId);
}
