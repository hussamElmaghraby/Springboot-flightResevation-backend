package com.elmaghraby.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elmaghraby.app.dao.RoleRepository;
import com.elmaghraby.app.entities.Role;
import com.elmaghraby.app.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role addRole(Role role) {
		roleRepository.save(role);
		return null;
	}

	@Override
	public List<Role> getAllRoles() {
		
		return roleRepository.findAll();
	}

	@Override
	public Role updateRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
		
	}
	
	@Override
	public Role createOrGetRoleUser() {
		Role role = roleRepository.findRoleByName("USER");
		if(role == null) {
			Role userRole = new Role();
			userRole.setName("USER");
			role = roleRepository.save(userRole);
		}
		return role;
	}

}
