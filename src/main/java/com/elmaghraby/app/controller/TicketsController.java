package com.elmaghraby.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elmaghraby.app.entities.Ticket;
import com.elmaghraby.app.service.TicketService;

@RestController
public class TicketsController {
	
	@Autowired 
	private TicketService ticketService;
	
	@PostMapping(value="/tickets/book/{connectionId}")
	public Ticket bookTicket(@PathVariable Long connectionId) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ticketService.bookTicket(connectionId , user.getUsername());
	}
	
	@GetMapping(value="/tickets")
	public List<Ticket> getAllCurrentUserTickets(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null) {
			throw new RuntimeException("Auth is null");
		}
		// auth.getName() =>  return the name of this principal ..
		return ticketService.getAllTicketsOfUser(auth.getName());
	}
	
	@DeleteMapping(value="ticket/{ticketId}/cancel")
	public void cancelBookTicket(@PathVariable("ticketId") Long id ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ticketService.cancelBookTicket(id , auth.getName());
	}
	
	@GetMapping(value="/admin/tickets")
	public List<Ticket> getAllTickets() {
		return ticketService.getAllTickets();
	}
	
	@DeleteMapping(value="/admin/tickets/{ticketId}")
	public void deleteTicket(@PathVariable("ticketId") Long ticketId) {
		ticketService.deleteTicket(ticketId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
