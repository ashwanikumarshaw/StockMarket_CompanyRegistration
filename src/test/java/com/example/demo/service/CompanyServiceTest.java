package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.exceptions.CompanyAlreadyExist;
import com.example.demo.exceptions.CompanyDoesNotExist;
import com.example.demo.exceptions.TurnoverLessThanLimit;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;

@AutoConfigureMockMvc
@SpringBootTest
class CompanyServiceTest {
	@Mock
	private CompanyRepository companyRepo;
	@InjectMocks
	private CompanyServiceImpl companyService;

	@Autowired
	private MockMvc mockmvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(companyService).build();
	}

	private List<Company> companylist = new ArrayList();

	@Test
	public void addCompanySuccess() throws Exception {
		Company company = new Company();
		company.setCompanyId(987);
		company.setCompanyName("DN");
		company.setCompanyCEO("Kira");
		company.setCompanyTurnover(800000000);
		company.setCompanyWebsite("www.dn.com");
		company.setStockExchange("BSE");
		company.setStockList(new HashSet<>());
		companylist.add(company);
		when(companyRepo.save(any())).thenReturn(company);

		Company company1 = companyService.addCompany(company);

		assertEquals(company, company1);
	}

	@Test
	public void addCompanyFailure() throws Exception {
		Company company = new Company();
		company.setCompanyId(987);
		company.setCompanyName("DN");
		company.setCompanyCEO("Kira");
		company.setCompanyTurnover(800000000);
		company.setCompanyWebsite("www.dn.com");
		company.setStockExchange("BSE");
		company.setStockList(new HashSet<>());
		companylist.add(company);
		when(companyRepo.save(any())).thenReturn(null);

		Company company1 = companyService.addCompany(company);

		assertNull(company1);
	}
	
	@Test
	public void getCompanySuccess() throws Exception {
		Company company = new Company();
		company.setCompanyId(987);
		company.setCompanyName("DN");
		company.setCompanyCEO("Kira");
		company.setCompanyTurnover(800000000);
		company.setCompanyWebsite("www.dn.com");
		company.setStockExchange("BSE");
		company.setStockList(new HashSet<>());
	
		when(companyRepo.findById(any())).thenReturn(Optional.of(company));
		Company company1 = companyService.getCompany(company.getCompanyId());
		assertEquals(company,company1);
	}
	
//	@Test
//	public void getCompanyFailure() throws Exception {
//		Company company = new Company();
//		company.setCompanyId(987);
//		company.setCompanyName("DN");
//		company.setCompanyCEO("Kira");
//		company.setCompanyTurnover(800000000);
//		company.setCompanyWebsite("www.dn.com");
//		company.setStockExchange("BSE");
//		company.setStockList(new HashSet<>());
//		when(companyRepo.findAllById(any())).thenReturn(null);
//		Company company1 = companyService.getCompany(company.getCompanyId());
//		assertNull(company1);
//	}
	
	@Test
	public void getAllCompanySuccess() throws Exception {
		Company company = new Company();
		company.setCompanyId(987);
		company.setCompanyName("DN");
		company.setCompanyCEO("Kira");
		company.setCompanyTurnover(800000000);
		company.setCompanyWebsite("www.dn.com");
		company.setStockExchange("BSE");
		company.setStockList(new HashSet<>());
		companylist.add(company);
		
		when(companyRepo.findAll()).thenReturn(companylist);

		List<Company> companyList = companyService.getAllCompanies();
		assertEquals(companylist,companyList);
	}
	
	@Test
	public void getAllCompanyFailure() throws Exception {
		when(companyRepo.findAll()).thenReturn(null);

		List<Company> companyList = companyService.getAllCompanies();
		assertNull(companyList);
	}
	
	@Test
	public void testDeleteCompany() throws CompanyDoesNotExist, CompanyAlreadyExist, TurnoverLessThanLimit {
		Company company = new Company();
		company.setCompanyId(987);
		company.setCompanyName("DN");
		company.setCompanyCEO("Kira");
		company.setCompanyTurnover(800000000);
		company.setCompanyWebsite("www.dn.com");
		company.setStockExchange("BSE");
		company.setStockList(new HashSet<>());
		companylist.add(company);

		when(companyRepo.findById(any())).thenReturn(Optional.of(company));

		companyService.removeCompany(987);

	    verify(companyRepo, times(1))
	            .deleteById(company.getCompanyId());
	}
	
	@Test
	public void testUpdateCompany() throws CompanyDoesNotExist, CompanyAlreadyExist, TurnoverLessThanLimit {
		Company company = new Company();
		company.setCompanyId(987);
		company.setCompanyName("DN");
		company.setCompanyCEO("Kira");
		company.setCompanyTurnover(800000000);
		company.setCompanyWebsite("www.dn.com");
		company.setStockExchange("BSE");
		company.setStockList(new HashSet<>());
		companylist.add(company);

		
		Company company1 = new Company();
		company1.setCompanyId(987);
		company1.setCompanyName("DxN");
		company1.setCompanyCEO("xKira");
		company1.setCompanyTurnover(900000000);
		company1.setCompanyWebsite("www.dxn.com");
		company1.setStockExchange("BSE");
		company1.setStockList(new HashSet<>());
		
		when(companyRepo.save(any())).thenReturn(company);
		Company compO=companyService.addCompany(company);

		when(companyRepo.findById(any())).thenReturn(Optional.of(company1));
		Company compU=companyService.updateCompany(company1);
		assertNotNull(compU);
		
//System.out.println(compO.getCompanyName()+"  "+compU.getCompanyName());
//		assertEquals(compO.getCompanyId(), compU.getCompanyId());
//		assertNotEquals(compO.getCompanyName(), compU.getCompanyName());
//		assertNotEquals(compO.getCompanyCEO(), compU.getCompanyCEO());
//		assertNotEquals(compO.getCompanyTurnover(), compU.getCompanyTurnover());
//		assertNotEquals(compO.getCompanyWebsite(), compU.getCompanyWebsite());
//		assertEquals(compO.getStockExchange(), compU.getStockExchange());
	
	}


}
