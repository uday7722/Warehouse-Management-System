package com.jsp.warehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.warehouse.models.Admin;
import java.util.List;
import java.util.Optional;

import com.jsp.warehouse.enums.AdminType;


public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	List<Admin> findByAdminType(AdminType adminType);

	boolean existsByAdminType(AdminType adminType);

	Optional<Admin> findByEmail(String username);

}
