package com.jsp.warehouse.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.warehouse.exception.StorageNotFoundByIdException;
import com.jsp.warehouse.exception.WareHouseNotFoundByIdException;
import com.jsp.warehouse.mapper.StorageMapper;
import com.jsp.warehouse.models.Storage;
import com.jsp.warehouse.models.WareHouse;
import com.jsp.warehouse.repo.StorageRepo;
import com.jsp.warehouse.repo.WareHouseRepo;
import com.jsp.warehouse.requestdto.StorageRequest;
import com.jsp.warehouse.responsedto.AdminResponse;
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

	@Override
	public ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest,int warehouseId,@RequestParam int noOfStorageUnits) {

		WareHouse wareHouse = wareHouseRepo.findById(warehouseId).orElseThrow(()->new WareHouseNotFoundByIdException("No warehouse found by the given id"));

		List<Storage> storages=new ArrayList<Storage>();

		while(noOfStorageUnits>0) {

			Storage storage = storageMapper.mapToStorage(storageRequest, new Storage());
			storage.setWareHouse(wareHouse);
			storage.setAvailableAreaInMetres(storageRequest.getLengthInMeters()*storageRequest.getBreadthInMeters()*storageRequest.getHeightInMeters());
			storage.setMaxAdditionalWeightInKg(storageRequest.getCapacityWeightInKg());


			storages.add(storage);

			noOfStorageUnits--;

		}

		storageRepo.saveAll(storages);

		return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleStructure<String>()
				.setStatus(HttpStatus.CREATED.value())
				.setMesssage(" "+noOfStorageUnits+" storages created"));


	}

	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateWarehouse(int storageId,StorageRequest storageRequest) {

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
