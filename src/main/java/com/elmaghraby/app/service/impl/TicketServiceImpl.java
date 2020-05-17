package com.elmaghraby.app.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elmaghraby.app.dao.ConnectionRepository;
import com.elmaghraby.app.dao.UserRepository;
import com.elmaghraby.app.entities.Connection;
import com.elmaghraby.app.entities.PriceTable;
import com.elmaghraby.app.entities.Ticket;
import com.elmaghraby.app.entities.User;
import com.elmaghraby.app.exception.BookedTooLateException;
import com.elmaghraby.app.exception.NoTicketAvailableException;
import com.elmaghraby.app.service.PriceTableService;
import com.elmaghraby.app.service.TicketRepository;
import com.elmaghraby.app.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@Autowired 
	private TicketRepository ticketRepository;
	
	@Autowired 
	private PriceTableService priceTableService;
	
	@Override
	public Ticket bookTicket(Long connectionId, String username) {
		Connection connection = connectionRepository.findConnectionById(connectionId);
		
		if(connection == null) {
			throw new RuntimeException("connection doesn't exists !!");
		}
		User  user = UserRepository.findUserByEmail(username);
		
		if(user == null ) {
			throw new RuntimeException("User doesn't exists !!");
		}
		
		//get the existing price table ..
		PriceTable priceTable = priceTableService.getPriceTable();
		
		Long minutesToDeparture = getMinutesDiffBetweenDates(connection.getDepartureDate() , new Date());
		
		if(minutesToDeparture < 30) {
			throw new BookedTooLateException();
		}
		
		int ticketCount = connection.getTickets() != null ? connection.getTickets().size() : 0;
		
		if(ticketCount+1 > connection.getPlaces()) {
			
			throw new NoTicketAvailableException();
		}
		
		Ticket ticket  =  new Ticket();
		ticket.setConnection(connection);
		ticket.setPrice(priceTable.getPricePerDistance().multiply(new BigDecimal(connection.getDistance())));
		ticket.setUser(user);
		ticket.setReverationDate(new Date());
		ticket.setTicketNumber(UUID.randomUUID().toString());
		return ticketRepository.save(ticket);
	}
	
	@Override
	public List<Ticket> getAllTicketsOfUser(String username) {
		User user = UserRepository.findUserByEmail(username);
		if(user == null ) {
			throw new RuntimeException("User doesn't exists !!");
		}
		return ticketRepository.findByUserId(user.getId());
	}
	
	@Override
	public void cancelBookTicket(Long ticketId, String username) {
		Ticket ticket  = ticketRepository.findTicketById(ticketId);
		User user = UserRepository.findUserByEmail(username);
		if(user == null) {
			throw new RuntimeException("User doesn't exists !!");
		}
		
		if(!ticket.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Cannot delete ticket that you are not the owner of !!");
		}
		ticketRepository.delete(ticket);
	}
	
	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
		
	}
	
	@Override
	public void deleteTicket(Long ticketId) {
		ticketRepository.deleteById(ticketId);
	}
	

	private Long getMinutesDiffBetweenDates(Date d1, Date d2) {
		Long diff = d1.getTime() - d2.getTime();
		
		return TimeUnit.MILLISECONDS.toMinutes(diff);
	}



	

	


	

}
