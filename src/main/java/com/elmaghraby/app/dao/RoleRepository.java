package com.elmaghraby.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elmaghraby.app.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long>{

	Role findRoleByName(String name);

}
