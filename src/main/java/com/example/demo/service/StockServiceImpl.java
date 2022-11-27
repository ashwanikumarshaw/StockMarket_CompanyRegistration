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
	@Autowired
	private CompanyService companyService;

	@Override
	public Stock addStock(Stock stock) throws CompanyDoesNotExist {
		if (companyService.validCompany(stock.getCompany_id_fk()))
			return stockRepository.saveAndFlush(stock);
		else
			throw new CompanyDoesNotExist();
	}

	@Override
	public Stock getLastStock(long company_id_fk) throws CompanyDoesNotExist {
		if (!companyService.validCompany(company_id_fk))
			throw new CompanyDoesNotExist();
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
