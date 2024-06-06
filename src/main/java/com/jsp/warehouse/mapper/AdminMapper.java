package com.jsp.warehouse.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsp.warehouse.models.Admin;
import com.jsp.warehouse.requestdto.AdminRequest;
import com.jsp.warehouse.responsedto.AdminResponse;

@Component
public class AdminMapper {
	
	@Autowired
	private PasswordEncoder encoder;
	
	public Admin mapToAdmin(AdminRequest adminRequest,Admin admin) {
		admin.setName(adminRequest.getName());
		admin.setEmail(adminRequest.getEmail());
		admin.setPassword(encoder.encode(adminRequest.getPassword()));
	
		return admin;
	}
	
	public AdminResponse mapToAdminResponse(Admin admin) {
		return AdminResponse.builder()
				.adminId(admin.getAdminId())
				.email(admin.getEmail())
				.name(admin.getName())
				.adminType(admin.getAdminType())
				.build();
	}

}
