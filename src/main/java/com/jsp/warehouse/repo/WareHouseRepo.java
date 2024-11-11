package com.jsp.warehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jsp.warehouse.models.WareHouse;

public interface WareHouseRepo extends JpaRepository<WareHouse, Integer> {

	

}
