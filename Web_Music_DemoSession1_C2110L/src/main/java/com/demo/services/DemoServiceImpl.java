package com.demo.services;

import org.springframework.stereotype.Service;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

	@Override
	public String hello() {
		return "Hello World";
	}

	@Override
	public String hi(String fullName) {
		return "Hi " + fullName;
	}

}
