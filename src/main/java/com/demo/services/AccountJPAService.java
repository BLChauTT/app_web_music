package com.demo.services;

import com.demo.entities.Account;

public interface AccountJPAService {

	public boolean save(Account account);
	
	public boolean login(String username, String password);
	
	public Account findByEmail(String email);
	
	public Account findByEmailAndToken(String email, String token);

	public Iterable<Account> findAll();
	
}
