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

import com.elmaghraby.app.dto.ConnectionDto;
import com.elmaghraby.app.service.ConnectionService;

@RestController
public class ConnectionController {

	@Autowired
	private ConnectionService connectionService;
	
	@PostMapping(value="/admin/connections")
	private ConnectionDto addConnection(@RequestBody ConnectionDto connectionDto) {
		return connectionService.addConnection(connectionDto);
	}
	
	@GetMapping(value="/connections")
	public List<ConnectionDto> getAllConnection(){
		return connectionService.getAllConnection();
	}
	
	@PutMapping(value="/admin/connections")
	public ConnectionDto updateConnection(@RequestBody ConnectionDto connectionDto) {
		return connectionService.updateConnection(connectionDto);
	}
	
	@DeleteMapping(value="/admin/connections/{id}")
	public void deleteConnection(@PathVariable("id") Long id) {
		connectionService.deleteConnection(id);
	}
}
