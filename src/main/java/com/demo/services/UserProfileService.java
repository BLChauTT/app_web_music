package com.demo.services;

import com.demo.entities.Userprofile;

public interface UserProfileService {
	
	public Userprofile findByAccountId(int accountId);
}
