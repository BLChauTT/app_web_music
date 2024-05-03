package com.demo.services;

import com.demo.entities.Song;
import com.demo.entities.Userprofile;

public interface UserProfileService {

	public Userprofile findByAccountId(int accountId);

	public Userprofile updateProfile(int id, Userprofile userprofile);
}
