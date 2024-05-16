package com.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Userprofile;
import com.demo.repositories.UserProfileRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserprofileServiceImpl implements UserProfileService {
	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public Userprofile findByAccountId(int accountId) {

		return userProfileRepository.findByAccountId(accountId);
	}

	@Override
	public Optional<Userprofile> updateFindByAccountId(int accountId) {
        return userProfileRepository.updateFindByAccountId(accountId);
    }

	@Override
	public Userprofile updateProfile(int accountId, Userprofile updatedProfile) {
		Optional<Userprofile> existingProfileOpt = userProfileRepository.updateFindByAccountId(accountId);
        if (existingProfileOpt.isPresent()) {
            Userprofile existingProfile = existingProfileOpt.get();
            existingProfile.setFullName(updatedProfile.getFullName());
            existingProfile.setAvatarUrl(updatedProfile.getAvatarUrl());
            existingProfile.setAddress(updatedProfile.getAddress());
            existingProfile.setPhoneNumber(updatedProfile.getPhoneNumber());
            return userProfileRepository.save(existingProfile);
        } else {
            throw new EntityNotFoundException("Profile not found for account id: " + accountId);
        }
	}

	@Override
	public String getAvatarUrlByAccountId(int accountId) {
		return userProfileRepository.findAvatarUrlByAccountId(accountId);
	}
}
