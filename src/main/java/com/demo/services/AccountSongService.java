package com.demo.services;

import java.util.List;

import com.demo.entities.AccountSong;

public interface AccountSongService {

	public List<AccountSong> findByAccountId(int accountId);

}
