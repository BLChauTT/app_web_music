package com.demo.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	@Override
	public List<Account> findByBirthday() {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	//public List<Account> findByBirthday() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		int day = calendar.get(Calendar.DAY_OF_MONTH);
//		int month = calendar.get(Calendar.MONTH) + 1;
//		return accountRepository.findByBirthday(day, month);
	//}

}
