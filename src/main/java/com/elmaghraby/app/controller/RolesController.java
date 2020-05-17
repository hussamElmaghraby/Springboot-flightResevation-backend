package com.elmaghraby.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elmaghraby.app.entities.Role;
import com.elmaghraby.app.service.RoleService;

@RestController
public class RolesController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping(value="/admin/roles")
	public Role addRole(@RequestBody Role role) {
		return roleService.addRole(role);
	}
	
	
	@GetMapping(value="/admin/roles")
	public List<Role> getAllRoles(){
		return roleService.getAllRoles();
	}
	
	@PutMapping(value="/admin/roles")
	public Role updateRole(@RequestBody Role role) {
		return roleService.updateRole(role);
	}
	
	@DeleteMapping(value="/admin/roles/{id}")
	public void deleteRole(@PathVariable("id") Long id) {
		roleService.deleteRole(id);
	}
	
}
