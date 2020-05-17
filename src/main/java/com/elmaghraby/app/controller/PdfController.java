package com.elmaghraby.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.elmaghraby.app.service.PdfService;

@RestController
public class PdfController {
	
	@Autowired
	private PdfService pdfService;
	
	
	@GetMapping(value="/ticket-pdf/{id}")
	public ResponseEntity<byte[]> getTicketPdf(@PathVariable("id") Long id){
		
		return pdfService.getTicketPdf(id);
	}
	
}
