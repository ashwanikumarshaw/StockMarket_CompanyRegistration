package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.CompanyDoesNotExist;
import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {
	@Autowired
	private StockRepository stockRepository;
	
	@Override
	public boolean addStock(Stock stock) throws CompanyDoesNotExist {
		stockRepository.saveAndFlush(stock);
		return true;

	}
	@Override
	public Stock getLastStock(long company_id_fk) {
		Set<Stock> stockSet = stockRepository.getStock(company_id_fk);
		Stock stock = new Stock();
		for (Stock st : stockSet) {
			stock = st;
			break;
		}
		return stock;
	}
	@Override
	public boolean removeAllStock(long companyId) {
		stockRepository.deleteAllStock(companyId);
		return true;
	}
}
