package com.elmaghraby.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elmaghraby.app.dao.ConnectionRepository;
import com.elmaghraby.app.dto.ConnectionDto;
import com.elmaghraby.app.entities.Connection;
import com.elmaghraby.app.service.ConnectionService;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	private ConnectionRepository connectionRepository;

	@Override
	public ConnectionDto addConnection(ConnectionDto connectionDto) {
		ModelMapper modelMapper = new ModelMapper();
		Connection connection = modelMapper.map(connectionDto, Connection.class);
		connection.setId(null);
		Connection savedConenction = connectionRepository.save(connection);

		return modelMapper.map(savedConenction, ConnectionDto.class);
	}

	@Override
	public List<ConnectionDto> getAllConnection() {
		ModelMapper modelMapper = new ModelMapper();
		List<ConnectionDto> dtos = new ArrayList<>();
		List<Connection> connections = connectionRepository.findAll();

		for (Connection connection : connections) {
			ConnectionDto connectionDto = modelMapper.map(connection, ConnectionDto.class);
			this.setTakenPlaces(connectionDto, connection);
			dtos.add(connectionDto);
		}
		return dtos;
	}

	@Override
	public ConnectionDto updateConnection(ConnectionDto connectionDto) {
		ModelMapper modelMapper = new ModelMapper();

		Connection connection = modelMapper.map(connectionDto, Connection.class);
		Connection updatedConnection = connectionRepository.save(connection);
		ConnectionDto updatedConnectionDto = modelMapper.map(updatedConnection, ConnectionDto.class);
		this.setTakenPlaces(updatedConnectionDto, updatedConnection);
		return updatedConnectionDto;
	}

	@Override
	public void deleteConnection(Long id) {
		connectionRepository.deleteById(id);
	}
	
	private void setTakenPlaces(ConnectionDto connectionDto , Connection connection) {
		connectionDto.setTakenPlaces(connection.getTickets() != null ? connection.getTickets().size() : 0);
	}
		
}
