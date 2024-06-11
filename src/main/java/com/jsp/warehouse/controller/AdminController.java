package com.jsp.warehouse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse.requestdto.AdminRequest;
import com.jsp.warehouse.responsedto.AdminResponse;
import com.jsp.warehouse.service.AdminService;
import com.jsp.warehouse.util.ResponseStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<AdminResponse>> saveSuperAdmin(@RequestBody AdminRequest request) {
		return service.saveSuperAdmin(request);		
	}
	
	@PreAuthorize("hasAuthority('CREATE_ADMIN')")
	@PostMapping("/{wareHouseId}/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@PathVariable int wareHouseId,@RequestBody AdminRequest request) {
		return service.createAdmin(wareHouseId,request);
	}
	

}
