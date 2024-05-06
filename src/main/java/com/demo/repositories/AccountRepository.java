package com.demo.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	@Query("from Account where email = :email and password = :password and status = :status")
	public Account login(@Param("email") String email, @Param("password") String password,
			@Param("status") boolean status);

	@Query("from Account where email = :email")
	public Account findByEmail(@Param("email") String email);
	
	@Query("from Account where username LIKE %:partialUsername%")
	public List<Account> findByPartialUsername(@Param("partialUsername") String partialUsername);

	@Query("from Account where email = :email and token = :token")
	public Account findByEmailAndToken(@Param("email") String email, @Param("token") String token);
	
	@Query("SELECT a FROM Account a")
	public Page<Account> findAllPaged(Pageable pageable);
}
