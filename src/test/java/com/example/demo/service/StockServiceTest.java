package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.demo.exceptions.CompanyAlreadyExist;
import com.example.demo.exceptions.TurnoverLessThanLimit;
import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.StockRepository;

@AutoConfigureMockMvc
@SpringBootTest
class StockServiceTest {
	@Mock
	private StockRepository stockRepository;
	@Mock
	private CompanyService companyService;
	@Mock
	private CompanyRepository companyRepo;

	@InjectMocks
	private StockServiceImpl stockService;

	@Autowired
	private MockMvc mockmvc;

	private List<Stock> stocklist = new ArrayList();
	Company company1 = new Company();

	@BeforeEach
	public void init() throws CompanyAlreadyExist, TurnoverLessThanLimit {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(stockService).build();

	}

	@Test

	public void testAddStockPrice() throws Exception

	{

		Stock stock = new Stock();

		stock.setTransactionId(100);

		stock.setStockPrice(100);

		stock.setCompany_id_fk(1001);

		when(stockRepository.save(any())).thenReturn(stock);

		boolean s1 = stockService.addStock(stock);

		assertTrue(s1);

	}

}
