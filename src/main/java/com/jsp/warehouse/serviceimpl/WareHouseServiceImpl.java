package com.jsp.warehouse.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse.exception.AdminNotFoundByIdException;
import com.jsp.warehouse.exception.WareHouseNotFoundByIdException;
import com.jsp.warehouse.mapper.WareHouseMapper;
import com.jsp.warehouse.models.WareHouse;
import com.jsp.warehouse.repo.WareHouseRepo;
import com.jsp.warehouse.requestdto.WareHouseRequest;
import com.jsp.warehouse.responsedto.AdminResponse;
import com.jsp.warehouse.responsedto.StorageResponse;
import com.jsp.warehouse.responsedto.WareHouseResponse;
import com.jsp.warehouse.service.WareHouseService;
import com.jsp.warehouse.util.ResponseStructure;

@Service
public class WareHouseServiceImpl implements WareHouseService {

	@Autowired
	private WareHouseRepo wareHouseRepo;

	@Autowired
	private WareHouseMapper wareHouseMapper;

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest request) {

		WareHouse wareHouse = wareHouseRepo.save(wareHouseMapper.mapToWarehouse(request, new WareHouse()));

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<WareHouseResponse>().setStatus(HttpStatus.CREATED.value())
						.setMessage("Warehouse Object created")
						.setData(wareHouseMapper.mapToWareHouseResponse(wareHouse)));

	}

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(int warehouseId,WareHouseRequest wareHouseRequest) {
		
		return wareHouseRepo.findById(warehouseId).map(existingWarehouse->{
			wareHouseMapper.mapToWarehouse(wareHouseRequest, existingWarehouse);
			
			existingWarehouse=wareHouseRepo.save(existingWarehouse);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<WareHouseResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Warehouse updated successfully")
					.setData(wareHouseMapper.mapToWareHouseResponse(existingWarehouse))
					);
		}).orElseThrow(()->new WareHouseNotFoundByIdException("No warehouse found by the given Id"));
			
	}

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouse(int warehouseId) {
		
		return 	wareHouseRepo.findById(warehouseId).map(warehouse->{


			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<WareHouseResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("warehouse found Successfully")
					.setData(wareHouseMapper.mapToWareHouseResponse(warehouse))
					);


		}).orElseThrow(()->new WareHouseNotFoundByIdException("No warehouse Found by the given Id"));
		

	}

	@Override
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findAllWarehouse() {
		
		 List<WareHouseResponse> wareHouseresponses = wareHouseRepo.findAll().stream().map(warehouse->wareHouseMapper.mapToWareHouseResponse(warehouse)).toList();
		
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<WareHouseResponse>>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("Warehouses found ").setData(wareHouseresponses));
	}

}
