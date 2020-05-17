package com.elmaghraby.app.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.elmaghraby.app.entities.Ticket;
import com.elmaghraby.app.service.PdfService;
import com.elmaghraby.app.service.TicketRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfServiceImpl implements PdfService {

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public ResponseEntity<byte[]> getTicketPdf(Long id) {

		Ticket ticket = ticketRepository.findTicketById(id);

		if (ticket == null) {
			throw new RuntimeException("ticket not found !!");
		}

		try {
			generatePdf(ticket);
			
			// retrieve content of PDF file ..
			//Path ==> used to locate a file in a file system
			Path path = Paths.get("C:/Users/Mohamed Rabea/Desktop/yourTicket.pdf");
			//ensures that the file is closed when all bytes have been read or an I/O error,
			//or other runtime exception
			byte[] contents = Files.readAllBytes(path);
			
			HttpHeaders headers = new HttpHeaders();
			// set content type  .. parse the 'applcaition/pdf' from string into single media type
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			String filename = "yourTicket.pdf";
			// set header when creating mutlipart/form-data request ..
			headers.setContentDispositionFormData(filename, filename);
			headers.setCacheControl("must-revalidate , post-ckeck=0 , pre-check=0");
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents ,headers ,HttpStatus.OK);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// if try-catch failed ..
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		return response;
	}

	private Document generatePdf(Ticket ticket) throws FileNotFoundException, DocumentException {
		Document document = new Document();

		PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Mohamed Rabea/Desktop/yourTicket.pdf"));
		// start writing in pdf
		document.open();
		PdfPTable table = new PdfPTable(2);
		PdfPCell cell = new PdfPCell(new Paragraph("Ticket"));
		// take all row space ..
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(10.0f);
		cell.setBackgroundColor(new BaseColor(140, 221, 8));
		table.addCell(cell);

		ArrayList<String[]> row = new ArrayList<>();
		String[] data = new String[2];

		data[0] = "Ticket NUmber";
		data[1] = ticket.getTicketNumber();
		row.add(data);

		data = new String[2];
		data[0] = "Price";
		data[1] = ticket.getPrice().toString() + "USD";
		row.add(data);

		data = new String[2];
		data[0] = "From : ";
		data[1] = ticket.getConnection().getStartPlace();
		row.add(data);

		data = new String[2];
		data[0] = "To :";
		data[1] = ticket.getConnection().getEndPlace();
		row.add(data);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd HH:mm");

		data = new String[2];
		data[0] = "Departure date:";
		data[1] = formatter.format(ticket.getConnection().getDepartureDate());
		row.add(data);

		data = new String[2];
		data[0] = "Arrival date :";
		data[1] = formatter.format(ticket.getConnection().getArrivalDate());
		row.add(data);

		for (int i = 0; i < row.size(); i++) {
			String[] cols = row.get(i);
			for (int j = 0; j < cols.length; j++) {
				table.addCell(cols[j]);
			}
		}

		document.add(table);
		document.close();

		return document;
	}

}
