package com.jsp.warehouse.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse.requestdto.WareHouseRequest;
import com.jsp.warehouse.responsedto.WareHouseResponse;
import com.jsp.warehouse.util.ResponseStructure;

public interface WareHouseService {

	ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest request);
	
	

}
