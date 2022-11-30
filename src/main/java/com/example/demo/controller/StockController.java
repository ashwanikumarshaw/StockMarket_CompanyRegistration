package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StockDto;
import com.example.demo.exceptions.CompanyDoesNotExist;
import com.example.demo.model.Stock;
import com.example.demo.service.CompanyService;
import com.example.demo.service.StockService;

@RestController
@RequestMapping("api/v1.0")
public class StockController {
	@Autowired
	private StockService stockService;
	@Autowired
	private CompanyService companyService;

	@PostMapping("/market/stock/add/{companycode}")
	public ResponseEntity<?> registerCompany(@PathVariable("companycode")long companyId, @RequestBody StockDto stockDto) throws CompanyDoesNotExist {
		
		if (!companyService.validCompany(companyId))
			throw new CompanyDoesNotExist();
		Stock stock=new Stock(companyId, stockDto.getStockPrice());
		
		if (stockService.addStock(stock))
			return new ResponseEntity<String>("Stock added", HttpStatus.CREATED);

		return new ResponseEntity<String>("Stock not created", HttpStatus.CONFLICT);

	}
}
