package com.demo.services;

import java.util.Optional;

import com.demo.entities.Userprofile;

public interface UserProfileService {

	public Userprofile findByAccountId(int accountId);

	public Userprofile updateProfile(int accountId, Userprofile updatedProfile);
	public String getAvatarUrlByAccountId(int accountId);
	
	public Optional<Userprofile> updateFindByAccountId(int accountId);
}
