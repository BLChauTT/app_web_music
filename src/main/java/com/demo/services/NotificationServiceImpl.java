package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Notification;
import com.demo.repositories.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public List<Notification> findByAccountId(int accountId) {
		return notificationRepository.findByAccountId(accountId);
	}

	@Override
	public boolean delete(int id) {
		try {
			notificationRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}









}
