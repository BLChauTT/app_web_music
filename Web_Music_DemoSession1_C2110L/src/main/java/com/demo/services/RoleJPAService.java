package com.demo.services;

import com.demo.entitiesjpa.Role;

public interface RoleJPAService {
	
	public Iterable<Role> findAll();
	
}
