package com.demo.services;

import java.util.List;

import com.demo.entities.Item;

public interface CartService {

	public int exists(int id, List<Item> cart);
	
	public double total(List<Item> cart);
	
}
