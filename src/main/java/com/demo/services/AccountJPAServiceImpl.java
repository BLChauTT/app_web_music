package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entitiesjpa.Account;
import com.demo.repositories.AccountRepository;

@Service
public class AccountJPAServiceImpl implements AccountJPAService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public boolean save(Account account) {
		try {
			accountRepository.save(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean login(String email, String password) {
		return accountRepository.login(email, password, true) != null;
	}

	@Override
	public Account findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}
	
}
