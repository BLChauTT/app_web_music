package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entitiesjpa.Role;
import com.demo.repositories.RoleRepository;

@Service
public class RoleJPAServiceImpl implements RoleJPAService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Iterable<Role> findAll() {
		return roleRepository.findAll();
	}

}
