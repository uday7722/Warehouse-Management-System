package com.jsp.warehouse.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse.requestdto.AdminRequest;
import com.jsp.warehouse.responsedto.AdminResponse;
import com.jsp.warehouse.util.ResponseStructure;

public interface AdminService {

	ResponseEntity<ResponseStructure<AdminResponse>> saveSuperAdmin(AdminRequest request);


	ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(int wareHouseId, AdminRequest request);


	ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest);


	ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest, int adminId);


	ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(int adminId);


	ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmin();

}
