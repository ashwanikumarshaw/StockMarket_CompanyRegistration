package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.CompanyAlreadyExist;
import com.example.demo.exceptions.CompanyDoesNotExist;
import com.example.demo.exceptions.TurnoverLessThanLimit;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepo;
	private Long TURNOVERLIMIT = (long) 100000000;

	@Override
	public Company addCompany(Company company) throws CompanyAlreadyExist, TurnoverLessThanLimit {

		if (validCompany(company.getCompanyId()))
			throw new CompanyAlreadyExist();
		else if (company.getCompanyTurnover() > TURNOVERLIMIT)
			return companyRepo.save(company);
		else
			throw new TurnoverLessThanLimit();

	}

	@Override
	public Company getCompany(long companyId) throws CompanyDoesNotExist {
		Optional<Company> company = companyRepo.findById(companyId);
		if (company.isPresent())
			return company.get();
		else
			throw new CompanyDoesNotExist();
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> companies = companyRepo.findAll();
		return companies;
	}

	@Override
	public boolean removeCompany(long companyId) throws CompanyDoesNotExist {
		if (!validCompany(companyId))
			throw new CompanyDoesNotExist();
		companyRepo.deleteById(companyId);
		return true;
	}

	@Override
	public boolean validCompany(long companyId) {
		Optional<Company> companyObj = companyRepo.findById(companyId);
		return companyObj.isPresent();
	}

	@Override
	public Company updateCompany(Company company) throws CompanyDoesNotExist, TurnoverLessThanLimit {
		if (!validCompany(company.getCompanyId()))
			throw new CompanyDoesNotExist();
		
		Optional<Company> companyObj = companyRepo.findById(company.getCompanyId());

		if (companyObj.isPresent()) {
		
			if (company.getCompanyTurnover() <= TURNOVERLIMIT)
				throw new TurnoverLessThanLimit();
						
			 companyRepo.save(company);
			 return company;
		}

		return null;
	}

}
