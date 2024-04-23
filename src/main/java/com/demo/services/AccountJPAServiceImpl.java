package com.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Account;
import com.demo.entities.Userprofile;
import com.demo.repositories.AccountRepository;
import com.demo.repositories.UserProfileRepository;

@Service
public class AccountJPAServiceImpl implements AccountJPAService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserProfileRepository userProfileRepository;

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
	public boolean saveUserProfile(Userprofile userprofile) {
		try {
			userProfileRepository.save(userprofile);
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

	@Override
	public Account findByEmailAndToken(String email, String token) {
		return accountRepository.findByEmailAndToken(email, token);
	}
	
	@Override
	public Iterable<Account> findAll() {
		return accountRepository.findAll();
	}

	
}
