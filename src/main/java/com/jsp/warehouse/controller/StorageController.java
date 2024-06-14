package com.jsp.warehouse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse.requestdto.StorageRequest;
import com.jsp.warehouse.responsedto.StorageResponse;
import com.jsp.warehouse.service.StorageService;
import com.jsp.warehouse.util.ResponseStructure;
import com.jsp.warehouse.util.SimpleStructure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1")
public class StorageController {
	
	@Autowired
	private StorageService storageService; 
	
	@PreAuthorize("hasAuthority('CREATE_STORAGE')")
	@PostMapping("/warehouses/{warehouseId}/storages")
	public ResponseEntity<SimpleStructure<String>> createStorage(@RequestBody StorageRequest storageRequest,@PathVariable int warehouseId,@RequestParam("no_of_storage_units") int noOfStorageUnits) {
		
		return storageService.createStorage(storageRequest,warehouseId,noOfStorageUnits);
	
	}
	
	@PreAuthorize("hasAuthority('UPDATE_STORAGE')")
	@PutMapping("storages/{storageId}")
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(@PathVariable int storageId, @RequestBody StorageRequest storageRequest) {
		
		return storageService.updateWarehouse(storageId,storageRequest);
	}
	

}