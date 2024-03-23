package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RectangleServiceImpl implements RectangleService {

	@Autowired
	private CalculateService calculateService;

	@Override
	public double area(double a, double b) {
		return calculateService.mul(a, b);
	}

	@Override
	public double perimeter(double a, double b) {
		return calculateService.mul(calculateService.add(a, b), 2);
	}

}
