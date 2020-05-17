package com.elmaghraby.app.service;

import org.springframework.http.ResponseEntity;

public interface PdfService {

	ResponseEntity<byte[]> getTicketPdf(Long id);

}
