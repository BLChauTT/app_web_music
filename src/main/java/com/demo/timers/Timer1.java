package com.demo.timers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Timer1 {

	@Async
	// @Scheduled(cron = "* * * * * *")
	public void display() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		System.out.println(simpleDateFormat.format(new Date()));
	}
	
	@Async
	@Scheduled(fixedDelay = 1000)
	public void display2() {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		System.out.println(simpleDateFormat.format(new Date()));
	}

}
