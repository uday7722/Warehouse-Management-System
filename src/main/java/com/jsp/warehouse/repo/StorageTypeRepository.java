package com.jsp.warehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse.models.StorageType;

public interface StorageTypeRepository extends JpaRepository<StorageType, Integer>{
	
	

}
