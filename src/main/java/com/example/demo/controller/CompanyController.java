package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.CompanyAlreadyExist;
import com.example.demo.exceptions.CompanyDoesNotExist;
import com.example.demo.exceptions.TurnoverLessThanLimit;
import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.CompanyService;
import com.example.demo.service.StockService;

@RestController
@RequestMapping("api/v1.0")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private StockService stockService;

//add
	@PostMapping("/market/company/register")
	public ResponseEntity<?> registerCompany(@RequestBody Company company)
			throws CompanyAlreadyExist, TurnoverLessThanLimit {

		if (companyService.addCompany(company) != null)
			return new ResponseEntity<Company>(company, HttpStatus.CREATED);

		return new ResponseEntity<String>("Company not created", HttpStatus.CONFLICT);

	}

//view with latest stock
	@GetMapping("/market/company/info/{companycode}")
	public ResponseEntity<?> viewCompany(@PathVariable("companycode") long companyId) throws CompanyDoesNotExist {

		Company company = companyService.getCompany(companyId);
		if (company != null) {
			Stock stock = stockService.getLastStock(companyId);
			company.getStockList().add(stock);
			return ResponseHandler.generateResponse("Successfully fetching the data", HttpStatus.OK, company);
//			CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.HOURS);
//			return ResponseEntity.ok().cacheControl(cacheControl)
//					.body(ResponseHandler.generateResponse("Successfully fetching the data", HttpStatus.OK, company));
		} else
			return new ResponseEntity<String>("Company doesn't exist", HttpStatus.NO_CONTENT);
	}

//viewall
	@GetMapping("/market/company/getAll")
	public ResponseEntity<?> viewAllCompanies() throws CompanyDoesNotExist {

		List<Company> companyList = companyService.getAllCompanies();
		if (!companyList.isEmpty()) {
			for (Company company : companyList) {
				Stock stock = stockService.getLastStock(company.getCompanyId());
				company.getStockList().add(stock);
			}

			CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.HOURS);
			return ResponseEntity.ok().cacheControl(cacheControl).body(
					ResponseHandler.generateResponse("Successfully fetching the data", HttpStatus.OK, companyList));
		} else
			return new ResponseEntity<String>("No company exist", HttpStatus.NO_CONTENT);
	}

//delete
	@DeleteMapping("/market/company/delete/{companycode}")
	public ResponseEntity<?> deleteCompany(@PathVariable("companycode") long companyId) throws CompanyDoesNotExist {
		if (!companyService.validCompany(companyId))
			throw new CompanyDoesNotExist();
		// 1st delete stock then delete company
		if (stockService.removeAllStock(companyId) && companyService.removeCompany(companyId)) {
			return new ResponseEntity<String>("Company deleted", HttpStatus.OK);

		}
		return new ResponseEntity<String>("Company not deleted", HttpStatus.INTERNAL_SERVER_ERROR);

	}

//update
	@PutMapping("/market/company/put/{companycode}")
	public ResponseEntity<?> updateCompany(@PathVariable("companycode") long companyId, @RequestBody Company company)
			throws TurnoverLessThanLimit, CompanyDoesNotExist {

		if (!companyService.validCompany(companyId))
			throw new CompanyDoesNotExist();

		company.setCompanyId(companyId);

		if (companyService.updateCompany(company) != null)
			return new ResponseEntity<Company>(company, HttpStatus.OK);

		return new ResponseEntity<String>("Company not created", HttpStatus.CONFLICT);

	}
}
