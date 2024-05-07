package com.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.entities.Account;
import com.demo.entities.Userprofile;

public interface AccountJPAService {

	public boolean save(Account account);

	public boolean login(String username, String password);

	public Account findByEmail(String email);

	public List<Account> findByusername(String username);

	public Account findByEmailAndToken(String email, String token);

	public List<Account> findAllList();

	public Iterable<Account> findAll();

	public boolean saveUserProfile(Userprofile userprofile);

	public Account findById(Integer accountId);

	public boolean remove(Account entity);

	public Page<Account> findPaginated(Pageable pageable);

	public long countTotalAccounts();

}
