package com.demo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Account findById(Integer accountId) {
		return accountRepository.findById(accountId).get();
	}

	@Override
	public boolean remove(Account entity) {
		try {
			entity.setStatus(false);
	        accountRepository.save(entity);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Account> findByusername(String username) {
		return accountRepository.findByPartialUsername(username);
	}

	@Override
	public List<Account> findAllList() {

		return accountRepository.findAll();
	}

	@Override
	public Page<Account> findPaginated(Pageable pageable) {
		return accountRepository.findAllPaged(pageable);
	}

	@Override
	public long countTotalAccounts() {
		return accountRepository.count();
	}

	//Phân Trang
	@Override
	public List<Account> findAccountsWithPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Account> pageAccounts = accountRepository.findAll(pageable);
		List<Account> accounts = pageAccounts.getContent();
		return accounts;
	}

}
