package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Userprofile;
import com.demo.repositories.UserProfileRepository;

@Service
public class UserprofileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;


	@Override
	public Userprofile findByAccountId(int accountId) {

		return userProfileRepository.findByAccountId(accountId);
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




}
