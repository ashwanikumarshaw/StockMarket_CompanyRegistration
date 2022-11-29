package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StockTest {

	@Test
	public void test01() {
		Stock stockObj = Mockito.mock(Stock.class);

		stockObj.setCompany_id_fk((long) 3);

		long mockCompany_id_fk = (long) 3;
		when(stockObj.getCompany_id_fk()).thenReturn(mockCompany_id_fk);

		assertEquals(stockObj.getCompany_id_fk(), mockCompany_id_fk);

	}

	@Test
	public void test02() {
		Stock stockObj = Mockito.mock(Stock.class);

		stockObj.setStockPrice((long) 30);

		long mockStockPrice = (long) 30;
		when(stockObj.getStockPrice()).thenReturn(mockStockPrice);

		assertEquals(stockObj.getStockPrice(), mockStockPrice);

	}

	@Test
	public void test03() {
		Stock stockObj = Mockito.mock(Stock.class);
		LocalDateTime timestamp = LocalDateTime.now();

		stockObj.setTimestamp(timestamp);

		when(stockObj.getTimestamp()).thenReturn(timestamp);

		assertEquals(stockObj.getTimestamp(), timestamp);

	}
}
