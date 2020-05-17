package com.elmaghraby.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elmaghraby.app.entities.PriceTable;
import com.elmaghraby.app.service.PriceTableService;

@RestController
public class PriceTableController {
	
	@Autowired
	private PriceTableService priceTableService;
	
	@PostMapping(value="/admin/pricetable")
	public PriceTable setPriceTable(@RequestBody PriceTable priceTable) {
		
		return priceTableService.updatePriceTable(priceTable);
	}
	
	@GetMapping("/pricetable")
	public PriceTable getPriceTable() {
		
		return priceTableService.getPriceTable();
	}
}
