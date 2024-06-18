package com.jsp.warehouse.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.warehouse.exception.StorageNotFoundByIdException;
import com.jsp.warehouse.exception.StorageTypeNotFoundByIdException;
import com.jsp.warehouse.exception.WareHouseNotFoundByIdException;
import com.jsp.warehouse.mapper.StorageMapper;
import com.jsp.warehouse.models.Storage;
import com.jsp.warehouse.models.StorageType;
import com.jsp.warehouse.models.WareHouse;
import com.jsp.warehouse.repo.StorageRepo;
import com.jsp.warehouse.repo.StorageTypeRepository;
import com.jsp.warehouse.repo.WareHouseRepo;
import com.jsp.warehouse.requestdto.StorageRequest;
import com.jsp.warehouse.responsedto.StorageResponse;
import com.jsp.warehouse.service.StorageService;
import com.jsp.warehouse.util.ResponseStructure;
import com.jsp.warehouse.util.SimpleStructure;

@Service
public class StorageServiceImpl implements StorageService{

	@Autowired
	private StorageRepo storageRepo;

	@Autowired
	private WareHouseRepo wareHouseRepo;

	@Autowired
	private StorageMapper storageMapper;
	
	@Autowired
	private StorageTypeRepository storageTypeRepository; 

	@Override
	public ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest,int warehouseId,int noOfStorageUnits,int storageTypeId) {

		WareHouse wareHouse = wareHouseRepo.findById(warehouseId).orElseThrow(()->new WareHouseNotFoundByIdException("No warehouse found by the given id"));
		
		StorageType storageType = storageTypeRepository.findById(storageTypeId).orElseThrow(()->new StorageTypeNotFoundByIdException("No storage found"));

		
		
		List<Storage> storages=new ArrayList<Storage>();
		
//		wareHouse.setTotalCapacityInKg(storageType.getCapacityWeightInKg()*noOfStorageUnits+wareHouse.getTotalCapacityInKg());
		
		int count=0;
		while(noOfStorageUnits>0) {

			Storage storage = storageMapper.mapToStorage(storageRequest, new Storage());
			
			storage.setWareHouse(wareHouse);
			storage.setStorageType(storageType);
			storageType.setUnitsAvailable(storageType.getUnitsAvailable()+noOfStorageUnits);
			storage.setMaxAdditionalWeightInKg(storageType.getCapacityWeightInKg());
			
			
			storages.add(storage);
			count++;
			noOfStorageUnits--;

		}
		
		storageRepo.saveAll(storages);
		wareHouseRepo.save(wareHouse);
		storageTypeRepository.save(storageType);

		return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleStructure<String>()
				.setStatus(HttpStatus.CREATED.value())
				.setMesssage(" "+count+" storages created"));

	}

	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(int storageId,StorageRequest storageRequest) {

		return storageRepo.findById(storageId).map(existingStorage->{
			existingStorage=storageMapper.mapToStorage(storageRequest, existingStorage);
			existingStorage=storageRepo.save(existingStorage);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Storage Successfully Updated")
					.setData(storageMapper.mapToStorageResponse(existingStorage))
					);	

		}).orElseThrow(()-> new StorageNotFoundByIdException("No storage found by the given id"));

	}

}


