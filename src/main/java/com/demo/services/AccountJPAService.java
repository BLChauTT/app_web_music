package com.demo.services;

import com.demo.entitiesjpa.Account;

public interface AccountJPAService {

	public boolean save(Account account);
	
	public boolean login(String username, String password);
	
	public Account findByEmail(String email);
	
}