package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;
	@Column(nullable = false)
	private long company_id_fk;
	@Column(nullable = false)
	private long stockPrice;
	@Column(nullable = false)
	private LocalDateTime timestamp;

	public Stock(long companyId, long stockPrice) {
		super();
		this.company_id_fk = companyId;
		this.stockPrice = stockPrice;
		this.timestamp = LocalDateTime.now();
	}

	public Stock() {
		super();
	}

	public long getCompany_id_fk() {
		return company_id_fk;
	}

	public void setCompany_id_fk(long company_id_fk) {
		this.company_id_fk = company_id_fk;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public long getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(long stockPrice) {
		this.stockPrice = stockPrice;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
