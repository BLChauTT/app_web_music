package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.entities.Account;

@Service
public class AccountServiceImpl implements AccountService {

	private List<Account> accounts;

	public AccountServiceImpl() {
		accounts = new ArrayList<Account>();
		accounts.add(new Account("acc1", "123", "Name 1"));
		accounts.add(new Account("acc2", "123", "Name 2"));
	}

	@Override
	public boolean login(String username, String password) {
		for (Account account : accounts) {
			if (account.getUsername().equals(username) 
				&& account.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

}
