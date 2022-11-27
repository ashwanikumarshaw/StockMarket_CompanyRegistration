package com.example.demo.service;

import com.example.demo.exceptions.CompanyDoesNotExist;
import com.example.demo.model.Stock;

public interface StockService {
	public Stock addStock(Stock stock) throws CompanyDoesNotExist;
	public Stock getLastStock(long company_id_fk) throws CompanyDoesNotExist;
	public boolean removeAllStock(long companyId);
}
