package com.elmaghraby.app.service;

import java.util.List;

import com.elmaghraby.app.dto.ConnectionDto;

public interface ConnectionService {

	ConnectionDto addConnection(ConnectionDto connectionDto);

	List<ConnectionDto> getAllConnection();

	ConnectionDto updateConnection(ConnectionDto connectionDto);

	void deleteConnection(Long id);

}
