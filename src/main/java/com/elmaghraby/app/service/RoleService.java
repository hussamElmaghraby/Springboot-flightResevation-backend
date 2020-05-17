package com.elmaghraby.app.service;

import java.util.List;

import com.elmaghraby.app.entities.Role;

public interface RoleService {
	Role addRole(Role role);
	List<Role> getAllRoles();
	Role updateRole(Role role);
	void deleteRole(Long id);
	Role createOrGetRoleUser();
}
