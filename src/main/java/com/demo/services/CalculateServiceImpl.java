package com.demo.services;

import org.springframework.stereotype.Service;

@Service("calculateService")
public class CalculateServiceImpl implements CalculateService {

	@Override
	public double add(double a, double b) {
		return a + b;
	}

	@Override
	public double mul(double a, double b) {
		return a * b;
	}

}
