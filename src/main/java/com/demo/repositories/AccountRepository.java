package com.demo.repositories;

import java.util.List;

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

	@Query("from Account where day(dob) = :day and month(dob) = :month")
	public List<Account> findByBirthday(@Param("day") int day, 
			@Param("month") int month);

}
