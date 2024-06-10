package com.jsp.warehouse.serviceimpl;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse.enums.AdminType;
import com.jsp.warehouse.exception.AdminNotFoundByIdException;
import com.jsp.warehouse.exception.IllegalOperationException;
import com.jsp.warehouse.exception.WareHouseNotFoundByIdException;
import com.jsp.warehouse.mapper.AdminMapper;
import com.jsp.warehouse.models.Admin;
import com.jsp.warehouse.models.WareHouse;
import com.jsp.warehouse.repo.AdminRepo;
import com.jsp.warehouse.repo.WareHouseRepo;
import com.jsp.warehouse.requestdto.AdminRequest;
import com.jsp.warehouse.responsedto.AdminResponse;
import com.jsp.warehouse.service.AdminService;
import com.jsp.warehouse.util.ResponseStructure;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepo repo; 

	@Autowired
	private AdminMapper mapper;

	@Autowired
	private WareHouseRepo wareHouseRepo;

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> saveSuperAdmin(AdminRequest request) {

		if(repo.existsByAdminType(AdminType.SUPER_ADMIN)) 
			throw new IllegalOperationException("Super admin already exists");

		Admin admin = mapper.mapToAdmin(request, new Admin());
		admin.setAdminType(AdminType.SUPER_ADMIN);
		admin=repo.save(admin);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AdminResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Admin Created")
				.setData(mapper.mapToAdminResponse(admin)));

	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(int wareHouseId,AdminRequest request) {

		Optional<WareHouse> warehouse = wareHouseRepo.findById(wareHouseId);
		if(warehouse.isPresent()) {
			WareHouse foundWarehouse = warehouse.get();

			Admin admin = mapper.mapToAdmin(request, new Admin());
			foundWarehouse.setAdmin(admin);
			repo.save(admin);

			wareHouseRepo.save(foundWarehouse);

			return  ResponseEntity.status(HttpStatus.CREATED).body(
					new ResponseStructure<AdminResponse>()
					.setStatus(HttpStatus.CREATED.value())
					.setMessage("Admin Created")
					.setData(mapper.mapToAdminResponse(admin)));
		}
		else {
			throw new WareHouseNotFoundByIdException("NO object found");
		}



	}

}

