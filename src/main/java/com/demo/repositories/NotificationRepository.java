package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {

	@Query("from Notification where account.accountId = :accountId")
    public List<Notification> findByAccountId(@Param("accountId") int accountId);
}
