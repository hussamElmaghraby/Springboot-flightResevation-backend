package com.elmaghraby.app.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elmaghraby.app.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket , Long>{

	List<Ticket> findByUserId(Long id);

	Ticket findTicketById(Long ticketId);

}
