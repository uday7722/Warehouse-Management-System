package com.jsp.warehouse.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse.requestdto.AdminRequest;
import com.jsp.warehouse.responsedto.AdminResponse;
import com.jsp.warehouse.util.ResponseStructure;

public interface AdminService {

	ResponseEntity<ResponseStructure<AdminResponse>> saveSuperAdmin(AdminRequest request);


	ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest request);

}
