package com.jsp.warehouse.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse.requestdto.WareHouseRequest;
import com.jsp.warehouse.responsedto.WareHouseResponse;
import com.jsp.warehouse.service.WareHouseService;
import com.jsp.warehouse.util.ResponseStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1")
public class WareHouseController {
	
	@Autowired
	private WareHouseService service;
	
	@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')") 
	@PostMapping("/warehouses")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> saveWareHouse(@RequestBody WareHouseRequest houseRequest ) {
	
		return service.createWareHouse(houseRequest);
		
	}
	

}
