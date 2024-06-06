package com.jsp.warehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse.models.Admin;
import java.util.List;
import com.jsp.warehouse.enums.AdminType;


public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	List<Admin> findByAdminType(AdminType adminType);

	boolean existsByAdminType(AdminType adminType);

}
