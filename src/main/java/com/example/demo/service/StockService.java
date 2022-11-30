package com.example.demo.service;

import com.example.demo.exceptions.CompanyDoesNotExist;
import com.example.demo.model.Stock;

public interface StockService {
	public boolean addStock(Stock stock) throws CompanyDoesNotExist;

	public Stock getLastStock(long companyId);

	public boolean removeAllStock(long companyId);
}
