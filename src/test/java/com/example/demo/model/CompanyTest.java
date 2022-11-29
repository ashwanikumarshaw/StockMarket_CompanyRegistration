package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CompanyTest {

	@Test
	public void test01() {
		Company companyObj = Mockito.mock(Company.class);// creating mock or dummy object

		companyObj.setCompanyName("CTS");
		
		String mockCompanyName = "CTS";
		when(companyObj.getCompanyName()).thenReturn(mockCompanyName);

		assertEquals(companyObj.getCompanyName(), mockCompanyName);

	}

	@Test
	public void test02() {
		Company companyObj = Mockito.mock(Company.class);// creating mock or dummy object

		companyObj.setCompanyCEO("Brain");
		
		String mockCompanyCEO = "Brain";
		when(companyObj.getCompanyCEO()).thenReturn(mockCompanyCEO);

		assertEquals(companyObj.getCompanyCEO(), mockCompanyCEO);

	}
	@Test
	public void test03() {
		Company companyObj = Mockito.mock(Company.class);// creating mock or dummy object

		companyObj.setCompanyTurnover((long)35000000);
		
		long mockCompanyTurnover = (long)35000000;
		when(companyObj.getCompanyTurnover()).thenReturn(mockCompanyTurnover);

		assertEquals(companyObj.getCompanyTurnover(), mockCompanyTurnover);

	}
	@Test
	public void test04() {
		Company companyObj = Mockito.mock(Company.class);// creating mock or dummy object

		companyObj.setCompanyWebsite("www.cts.com");
		
		String mockCompanyWebsite = "www.cts.com";
		when(companyObj.getCompanyWebsite()).thenReturn(mockCompanyWebsite);

		assertEquals(companyObj.getCompanyWebsite(), mockCompanyWebsite);

	}
	@Test
	public void test05() {
		Company companyObj = Mockito.mock(Company.class);// creating mock or dummy object

		companyObj.setStockExchange("BSE");
		
		String mockStockExchange = "BSE";
		when(companyObj.getStockExchange()).thenReturn(mockStockExchange);

		assertEquals(companyObj.getStockExchange(), mockStockExchange);

	}
}
