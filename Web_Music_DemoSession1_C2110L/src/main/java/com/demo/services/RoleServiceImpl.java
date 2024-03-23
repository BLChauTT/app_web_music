package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.entities.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Override
	public List<Role> findAll() {
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role(1, "Role 1"));
		roles.add(new Role(2, "Role 2"));
		roles.add(new Role(3, "Role 3"));
		roles.add(new Role(4, "Role 4"));
		roles.add(new Role(5, "Role 5"));
		return roles;
	}

}
