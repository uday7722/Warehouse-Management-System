package com.jsp.warehouse.serviceimpl;





import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
	private AdminRepo adminRepo; 

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private WareHouseRepo wareHouseRepo;

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> saveSuperAdmin(AdminRequest request) {

		if(adminRepo.existsByAdminType(AdminType.SUPER_ADMIN)) 
			throw new IllegalOperationException("Super admin already exists");

		Admin admin = adminMapper.mapToAdmin(request, new Admin());
		admin.setAdminType(AdminType.SUPER_ADMIN);
		admin=adminRepo.save(admin);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AdminResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Admin Created")
				.setData(adminMapper.mapToAdminResponse(admin)));

	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(int wareHouseId,AdminRequest request) {

		WareHouse house=wareHouseRepo.findById(wareHouseId).orElseThrow(()->new WareHouseNotFoundByIdException("No object found"));

		Admin admin=adminMapper.mapToAdmin(request, new Admin());
		admin.setAdminType(AdminType.ADMIN);
		admin=adminRepo.save(admin);
		house.setAdmin(admin);
		wareHouseRepo.save(house);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AdminResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Admin object Created")
				.setData(adminMapper.mapToAdminResponse(admin)));



	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return adminRepo.findByEmail(email).map(existingAdmin->{
			existingAdmin = adminMapper.mapToAdmin(adminRequest, existingAdmin);

			existingAdmin=adminRepo.save(existingAdmin);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<AdminResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Admin Successfully Updated")
					.setData(adminMapper.mapToAdminResponse(existingAdmin))
					);		
		}).orElseThrow(()->new IllegalOperationException("Can't perform Operation"));


	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest,int adminId) {

		return 	adminRepo.findById(adminId).map(existingAdmin->{
			existingAdmin = adminMapper.mapToAdmin(adminRequest, existingAdmin);

			existingAdmin=adminRepo.save(existingAdmin);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<AdminResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Admin Updated Successfully")
					.setData(adminMapper.mapToAdminResponse(existingAdmin))
					);


		}).orElseThrow(()->new AdminNotFoundByIdException("No Admin Found by the given Id"));




	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(int adminId) {

		return 	adminRepo.findById(adminId).map(admin->{


			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<AdminResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Admin Updated Successfully")
					.setData(adminMapper.mapToAdminResponse(admin))
					);


		}).orElseThrow(()->new AdminNotFoundByIdException("No Admin Found by the given Id"));
		


	}

	@Override
	public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmin() {
		
		List<AdminResponse> adminResponses= adminRepo.findAll().stream().map(admins->adminMapper.mapToAdminResponse(admins)).toList();
		
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<AdminResponse>>().setStatus(HttpStatus.FOUND.value()).setData(adminResponses));
		
		
	}

}

