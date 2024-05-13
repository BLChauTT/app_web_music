package com.demo.services;

import java.util.List;

import com.demo.entities.AccountSong;
import com.demo.entities.Comment;
import com.demo.entities.Rating;

public interface AccountSongService {
	public List<AccountSong> findByAccountId(int accountId);
	public boolean save(AccountSong accountSong);
	public List<AccountSong> findBySongId(int songId);
	public List<AccountSong> findAll();
	public List<AccountSong> findByTitle(String title);
	public List<AccountSong> findSongsWithPagination(int offset, int pageSize);
	public boolean deleteAccountSongAndRelatedData(int accountSongId);
}
