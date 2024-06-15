package com.jsp.warehouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse.exception.StorageTypeNotFoundByIdException;
import com.jsp.warehouse.exception.StorageTypeNotFoundException;
import com.jsp.warehouse.mapper.StorageTypeMapper;
import com.jsp.warehouse.models.StorageType;
import com.jsp.warehouse.repo.StorageTypeRepository;
import com.jsp.warehouse.requestdto.StorageTypeRequest;
import com.jsp.warehouse.responsedto.StorageTypeResponse;
import com.jsp.warehouse.service.StorageTypeService;
import com.jsp.warehouse.util.ResponseStructure;

@Service
public class StorageTypeServiceImpl implements StorageTypeService{
	
	@Autowired
	private StorageTypeRepository storageTypeRepo;
	
	@Autowired
	private StorageTypeMapper storageTypeMapper;

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(StorageTypeRequest storageTypeRequest) {
		
		StorageType storageType = storageTypeRepo.save(storageTypeMapper.mapToStorageRequest(storageTypeRequest, new StorageType()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<StorageTypeResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("StorageType created")
				.setData(storageTypeMapper.mapToStorageTypeResonse(storageType)));	
	}

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(int storageTypeId,StorageTypeRequest storageTypeRequest) {
		
		return storageTypeRepo.findById(storageTypeId).map(existingStorageType->{
			
			existingStorageType=storageTypeMapper.mapToStorageRequest(storageTypeRequest, existingStorageType);
			existingStorageType=storageTypeRepo.save(existingStorageType);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageTypeResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("StorageType updated successfully")
					.setData(storageTypeMapper.mapToStorageTypeResonse(existingStorageType)));	
		}).orElseThrow(()->new StorageTypeNotFoundByIdException("No Storagetype found by the given id"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findAllStorageTypes() {
		
		List<StorageTypeResponse> storageTypeResponses = storageTypeRepo.findAll().stream().map(storageTypes->storageTypeMapper.mapToStorageTypeResonse(storageTypes)).toList();
		
		if(storageTypeResponses.isEmpty())
			throw new StorageTypeNotFoundException("No storagetypes found");
		
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<StorageTypeResponse>>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("StorageTypes found")
				.setData(storageTypeResponses));
		
	}

}
