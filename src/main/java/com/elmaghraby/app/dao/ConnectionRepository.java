package com.elmaghraby.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.elmaghraby.app.entities.Connection;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

	Connection findConnectionById(Long connectionId);


}
