package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Company;

@Repository //Data-Access-Object-->CRUD operation + @component (bean)
@Transactional //apply ACID properties (if failes rollback) 
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
