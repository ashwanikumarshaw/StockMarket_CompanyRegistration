package com.example.demo.repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Stock;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query(value = "select st from Stock st where st.company_id_fk= :company_id_fk order by st.transactionId desc")
	public Set<Stock> getStock(long company_id_fk);
	
	@Modifying
	@Query(value="delete from Stock where company_id_fk= :companyId")
	public void deleteAllStock(long companyId);
	
}
