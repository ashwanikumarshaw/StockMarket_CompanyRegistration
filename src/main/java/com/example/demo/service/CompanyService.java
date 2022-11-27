package com.example.demo.service;

import java.util.List;

import com.example.demo.exceptions.CompanyAlreadyExist;
import com.example.demo.exceptions.CompanyDoesNotExist;
import com.example.demo.exceptions.TurnoverLessThanLimit;
import com.example.demo.model.Company;

public interface CompanyService {
	public Company addCompany(Company company) throws CompanyAlreadyExist,TurnoverLessThanLimit;
	public Company getCompany(long companyId) throws CompanyDoesNotExist;
	public List<Company> getAllCompanies();
	public boolean removeCompany(long companyId) throws CompanyDoesNotExist;
	public Company updateCompany(Company company) throws CompanyDoesNotExist,TurnoverLessThanLimit;
	public boolean validCompany(long companyId);
}
