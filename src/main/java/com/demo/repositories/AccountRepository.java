package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entitiesjpa.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

	@Query("from Account where email = :email and password = :password and status = :status")
	public Account login(@Param("email") String email, @Param("password") String password,
			@Param("status") boolean status);

	@Query("from Account where email = :email")
	public Account findByEmail(@Param("email") String email);

}
