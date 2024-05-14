package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.AccountSong;
import com.demo.repositories.AccountSongRepository;
import com.demo.repositories.CommentRepository;
import com.demo.repositories.FavoriteRepository;
import com.demo.repositories.RatingRepository;
import com.demo.repositories.SongDetailRepository;
import com.demo.repositories.SongRepository;
import com.demo.repositories.SongSingerRepository;

@Service
public class AccountSongServiceImpl implements AccountSongService {
	@Autowired
	private AccountSongRepository accountSongRepository;
	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private FavoriteRepository favoriteRepository;
	@Autowired
	private SongRepository songRepository;
	@Autowired
	private SongSingerRepository songSingerRepository;

	@Autowired
	private SongDetailRepository songDetailRepository;

	@Override
	public List<AccountSong> findByAccountId(int accountId) {
		return accountSongRepository.findByAccountId(accountId);
	}

	@Override
	public boolean save(AccountSong accountSong) {
		try {
			accountSongRepository.save(accountSong);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<AccountSong> findBySongId(int songId) {
		return accountSongRepository.findBySongId(songId);
	}

	@Override
	public List<AccountSong> findAll() {
		return accountSongRepository.findAll();
	}

	@Override
	public List<AccountSong> findByTitle(String title) {
		return accountSongRepository.findBySongDetail(title);
	}

	@Override
	public List<AccountSong> findSongsWithPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<AccountSong> pageSongs = accountSongRepository.findAll(pageable);
		List<AccountSong> songs = pageSongs.getContent();
		return songs;
	}

	@Transactional
	@Override
	public boolean deleteAccountSongAndRelatedData(int accountSongId) {
		try {

			AccountSong accountSong = accountSongRepository.findById(accountSongId).orElse(null);
			if (accountSong != null) {
				// Xóa các bình luận liên quan trước
				favoriteRepository.deleteByAccountSong_AccountSongId(accountSongId);
				commentRepository.deleteByAccountSong_AccountSongId(accountSongId);
				ratingRepository.deleteByAccountSong_AccountSongId(accountSongId);

				int songId = accountSong.getSong().getSongId();
				int songDetailId = accountSong.getSong().getSongdetail().getSongDetailId();
				accountSongRepository.deleteById(accountSongId);
				songSingerRepository.deleteBySongId(songId);
				songRepository.deleteById(songId);
				songDetailRepository.deleteById(songDetailId);

				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
