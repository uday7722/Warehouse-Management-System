package com.jsp.warehouse.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse.requestdto.WareHouseRequest;
import com.jsp.warehouse.responsedto.WareHouseResponse;
import com.jsp.warehouse.service.WareHouseService;
import com.jsp.warehouse.util.ResponseStructure;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1")
public class WareHouseController {
	
	@Autowired
	private WareHouseService service;
	
	@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')") 
	@PostMapping("/warehouses")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> saveWareHouse(@RequestBody @Valid WareHouseRequest houseRequest ) {
	
		return service.createWareHouse(houseRequest);
		
	}
	
	@PreAuthorize("hasAuthority('UPDATE_WAREHOUSE')")
	@PutMapping("/warehouses/{warehouseId}")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWarehouse(@RequestBody @Valid WareHouseRequest wareHouseRequest,@PathVariable int warehouseId) {
		
		return service.updateWareHouse(warehouseId,wareHouseRequest);
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping("/warehouses/{warehouseId}")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouse(@PathVariable int warehouseId) {	
		return service.findWarehouse(warehouseId);
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping("/warehouses")
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findAllWarehouse() {	
		return service.findAllWarehouse();
	}
	
	
	
	
	

}
