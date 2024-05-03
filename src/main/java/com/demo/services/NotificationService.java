package com.demo.services;

import java.util.List;

import com.demo.entities.Notification;


public interface NotificationService {

	public List<Notification> findByAccountId(int accountId);
	
	public boolean delete(int id);
}
