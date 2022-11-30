package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.Company;
import com.example.demo.service.CompanyService;
import com.example.demo.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class CompanyControllerTest {

	@Mock
	private CompanyService companyService;
	@Mock
	private StockService stockService;

	@InjectMocks
	private CompanyController companyController;

	@Autowired
	private MockMvc mockmvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(companyController).build();
	}

	private List<Company> companylist = new ArrayList();

	@Test
	public void viewAllCompanySuccess() throws Exception {
		Company company = new Company();
		company.setCompanyId(987);
		company.setCompanyName("DN");
		company.setCompanyCEO("Kira");
		company.setCompanyTurnover(800000000);
		company.setCompanyWebsite("www.dn.com");
		company.setStockExchange("BSE");
		company.setStockList(new HashSet<>());
		companylist.add(company);

		when(companyService.getAllCompanies()).thenReturn(companylist);

		List<Company> compList = companyService.getAllCompanies();

		assertEquals(companylist, compList);

		mockmvc.perform(
				MockMvcRequestBuilders.get("/api/v1.0/market/company/getAll").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void viewAllCompanyFailure() throws Exception {

		companylist.clear();

		when(companyService.getAllCompanies()).thenReturn(companylist);

		List<Company> compList = companyService.getAllCompanies();

		assertEquals(companylist, compList);

		mockmvc.perform(
				MockMvcRequestBuilders.get("/api/v1.0/market/company/getAll").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());

	}

	@Test
	public void viewCompanySuccess() throws Exception {
		Company company = new Company();
		company.setCompanyId(100);
		company.setCompanyName("DN");
		company.setCompanyCEO("Kira");
		company.setCompanyTurnover(800000000);
		company.setCompanyWebsite("www.dn.com");
		company.setStockExchange("BSE");
		company.setStockList(new HashSet<>());
		companylist.add(company);

		when(companyService.getCompany(100)).thenReturn(company);

		Company comp = companyService.getCompany(100);

		assertEquals(company, comp);

		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/info/{companycode}", 100)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void viewCompanyFailure() throws Exception {

		companylist.clear();

		when(companyService.getCompany(100)).thenReturn(null);

		Company comp = companyService.getCompany(100);

		assertNull(comp);

		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/info/{companycode}", 100)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNoContent());

	}
//	@Test
//	public void deleteCompanySuccess() throws Exception {
//		
//		when(companyService.removeCompany(1)).thenReturn(true);
//
//		boolean comp = companyService.removeCompany(1);
//
//		assertTrue(comp);
//		
//		mockmvc.perform(MockMvcRequestBuilders.delete("/api/v1.0/market/company/delete/{companycode}",1))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}

	@Test
	public void registerCompanySuccess() throws Exception {
		Company company = new Company();
		company.setCompanyId(987);
		company.setCompanyName("DN");
		company.setCompanyCEO("Kira");
		company.setCompanyTurnover(800000000);
		company.setCompanyWebsite("www.dn.com");
		company.setStockExchange("BSE");
		company.setStockList(new HashSet<>());
		companylist.add(company);

		when(companyService.addCompany(any())).thenReturn(company);

		Company comp = companyService.addCompany(company);

		assertEquals(company, comp);

		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(company)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void registerCompanyFailure() throws Exception {
		Company company = new Company();
		company.setCompanyId(987);
		company.setCompanyName("DN");
		company.setCompanyCEO("Kira");
		company.setCompanyTurnover(800000000);
		company.setCompanyWebsite("www.dn.com");
		company.setStockExchange("BSE");
		company.setStockList(new HashSet<>());
		companylist.add(company);

		when(companyService.addCompany(any())).thenReturn(null);

		Company comp = companyService.addCompany(company);

		assertNull(comp);

		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(company)))
				.andExpect(MockMvcResultMatchers.status().isConflict());
	}

	
}
