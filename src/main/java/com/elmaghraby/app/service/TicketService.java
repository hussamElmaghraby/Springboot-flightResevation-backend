package com.elmaghraby.app.service;

import java.util.List;

import com.elmaghraby.app.entities.Ticket;

public interface TicketService {

	Ticket bookTicket(Long connectionId, String username);

	List<Ticket> getAllTicketsOfUser(String name);

	void cancelBookTicket(Long id, String name);

	void deleteTicket(Long ticketId);

	List<Ticket> getAllTickets();

}
