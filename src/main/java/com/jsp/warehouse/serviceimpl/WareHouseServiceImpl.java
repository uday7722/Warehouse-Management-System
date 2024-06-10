package com.jsp.warehouse.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.jsp.warehouse.repo.WareHouseRepo;
import com.jsp.warehouse.requestdto.WareHouseRequest;
import com.jsp.warehouse.responsedto.WareHouseResponse;
import com.jsp.warehouse.service.WareHouseService;
import com.jsp.warehouse.util.ResponseStructure;

public class WareHouseServiceImpl implements WareHouseService{
	
	@Autowired
	private WareHouseRepo houseRepo;
	
	@Autowired
	private WareHou

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest request) {
		
		
		
	}

}
