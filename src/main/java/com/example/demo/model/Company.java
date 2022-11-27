package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {
	@Id
	private long companyId;
	@Column(nullable = false)
	private String companyName;
	@Column(nullable = false)
	private String companyCEO;
	@Column(nullable = false)
	private Long companyTurnover;
	@Column(nullable = false)
	private String companyWebsite;
	@Column(nullable = false)
	private String stockExchange;
	
	@OneToMany(targetEntity = Stock.class,cascade = CascadeType.ALL)
	private Set<Stock> stockList;
	
	public Set<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(Set<Stock> stockList) {
		this.stockList = stockList;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCEO() {
		return companyCEO;
	}

	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}

	public Long getCompanyTurnover() {
		return companyTurnover;
	}

	public void setCompanyTurnover(Long companyTurnover) {
		this.companyTurnover = companyTurnover;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

}
