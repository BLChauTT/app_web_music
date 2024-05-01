package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;
import com.demo.entities.Userprofile;

@Repository
public interface UserProfileRepository extends CrudRepository<Userprofile, Integer> {

	@Query("from Userprofile where account.accountId = :accountId")
	public Userprofile findByAccountId(@Param("accountId") int accountId);
}
