package com.jsp.warehouse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse.requestdto.AdminRequest;
import com.jsp.warehouse.responsedto.AdminResponse;
import com.jsp.warehouse.service.AdminService;
import com.jsp.warehouse.util.ResponseStructure;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/v1")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<AdminResponse>> saveSuperAdmin(@RequestBody @Valid AdminRequest request) {
		return service.saveSuperAdmin(request);		
	}
	
	@PreAuthorize("hasAuthority('CREATE_ADMIN')")
	@PostMapping("/warehouses/{warehouseId}/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@PathVariable int warehouseId,@RequestBody @Valid AdminRequest request) {
		return service.createAdmin(warehouseId,request);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_ADMIN')")
	@PutMapping("/updateadmins")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin( @RequestBody @Valid AdminRequest adminRequest) {
	
		return service.updateAdmin(adminRequest);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_ADMIN')")
	@PutMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(@RequestBody @Valid AdminRequest adminRequest,@PathVariable int adminId) {
	
		return service.updateAdminBySuperAdmin(adminRequest,adminId);
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(@PathVariable int adminId) {
		return service.findAdmin(adminId);
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping("/admins")
	public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmin() {
		return service.findAllAdmin();
	}
	
	
	
	
	

}
