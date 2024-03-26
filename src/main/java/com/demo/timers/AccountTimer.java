package com.demo.timers;

import java.text.SimpleDateFormat;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.entitiesjpa.Account;
import com.demo.services.AccountJPAService;

@Component
public class AccountTimer {

	private AccountJPAService accountJPAService;

	@Async
	// @Scheduled(fixedDelay = 1000 * 60)
	@Scheduled(cron = "0 * * * * *")
	public void display2() {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		System.out.println("Cac tai khoan sinh nhat hôm nay");
//		for (Account account : accountJPAService.findByBirthday()) {
//			System.out.println("full name: " + account.getFullName());
//			System.out.println("email: " + account.getEmail());
//			System.out.println("dob: " + simpleDateFormat.format(account.getDob()));
//			System.out.println("--------------------------");
//		}
	}

}
