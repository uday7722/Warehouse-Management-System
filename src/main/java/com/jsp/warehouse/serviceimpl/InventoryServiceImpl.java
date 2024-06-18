package com.jsp.warehouse.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse.exception.InventoriesNotFoundException;
import com.jsp.warehouse.exception.InventoryNotFoundByIdException;
import com.jsp.warehouse.exception.StorageNotFoundByIdException;
import com.jsp.warehouse.mapper.InventoryMapper;
import com.jsp.warehouse.models.Inventory;
import com.jsp.warehouse.models.Storage;
import com.jsp.warehouse.repo.InventoryRepo;
import com.jsp.warehouse.repo.StorageRepo;
import com.jsp.warehouse.requestdto.InventoryRequest;
import com.jsp.warehouse.responsedto.InventoryResponse;
import com.jsp.warehouse.service.InventoryService;
import com.jsp.warehouse.util.ResponseStructure;

@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	private InventoryRepo inventoryRepo;

	@Autowired
	private InventoryMapper inventoryMapper;

	@Autowired
	private StorageRepo storageRepo;

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(InventoryRequest inventoryRequest,int storageId) {

		Storage storage = storageRepo.findById(storageId).orElseThrow(()->new StorageNotFoundByIdException("No storage found by the given id"));

		Inventory inventory = inventoryRepo.save(inventoryMapper.mapToInventory(inventoryRequest, new Inventory()));

		inventory.setRestockedAt(LocalDate.now());

		storage.getInventories().add(inventory);


		storage.setMaxAdditionalWeightInKg(storage.getMaxAdditionalWeightInKg()-inventoryRequest.getWeightInKg()*inventoryRequest.getQuantity());		
		storage.setAvailableAreaInMetres(inventoryRequest.getLengthInMeters()*inventoryRequest.getBreadthInMeters()*inventoryRequest.getHeightInMeters());
		storage.setSellerId(inventory.getSellerId());
		inventory=inventoryRepo.save(inventory);
		storageRepo.save(storage);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<InventoryResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Inventory created")
				.setData(inventoryMapper.mapToInventoryResponse(inventory))
				);
	}

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,int productId) {

		return inventoryRepo.findById(productId).map(existingInventory->{

			existingInventory=inventoryMapper.mapToInventory(inventoryRequest, existingInventory);
			
			
			
			
			existingInventory=inventoryRepo.save(existingInventory);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<InventoryResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Updated successfully")
					.setData(inventoryMapper.mapToInventoryResponse(existingInventory)));

		}).orElseThrow(()->new InventoryNotFoundByIdException("No inventory found by the given id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(int productId) {

		Inventory inventory = inventoryRepo.findById(productId).orElseThrow(()-> new InventoryNotFoundByIdException("No inventory found by the given id"));

		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<InventoryResponse>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("Inventories found and displayed")
				.setData(inventoryMapper.mapToInventoryResponse(inventory))
				);

	}

	@Override
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventories() {

		List<InventoryResponse> inventoryResponses = inventoryRepo.findAll().stream().map(inventories->inventoryMapper.mapToInventoryResponse(inventories)).toList();

		if(inventoryResponses.isEmpty())
			throw new InventoriesNotFoundException("No inventories are present in the database");

		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<InventoryResponse>>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("Found and displayed")
				.setData(inventoryResponses));


	}

}
