package com.jsp.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse.requestdto.StorageTypeRequest;
import com.jsp.warehouse.responsedto.StorageTypeResponse;
import com.jsp.warehouse.service.StorageTypeService;
import com.jsp.warehouse.util.ResponseStructure;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/v1")
public class StorageTypeController {
	
	@Autowired
	private StorageTypeService storageTypeService;
	
	@PostMapping("/storagetypes")
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(@RequestBody StorageTypeRequest storageTypeRequest) {
		
		return storageTypeService.createStorageType(storageTypeRequest);
	}
	
	@PutMapping("storagetypes/{storageTypeId}")
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(@PathVariable int storageTypeId, @RequestBody StorageTypeRequest storageTypeRequest) {
		
		return storageTypeService.updateStorageType(storageTypeId,storageTypeRequest);
	}
	
	@GetMapping("/storagetypes")
	public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findAllStorageTypes() {
		return storageTypeService.findAllStorageTypes();
	}
	
	
	

}
