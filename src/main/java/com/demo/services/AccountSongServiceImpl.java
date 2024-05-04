package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.AccountSong;
import com.demo.entities.Userprofile;
import com.demo.repositories.AccountSongRepository;
import com.demo.repositories.UserProfileRepository;

@Service
public class AccountSongServiceImpl implements AccountSongService {
	
	@Autowired
	private AccountSongRepository accountSongRepository;

	@Override
	public List<AccountSong> findByAccountId(int accountId) {
		return accountSongRepository.findByAccountId(accountId);
	}

	







}
