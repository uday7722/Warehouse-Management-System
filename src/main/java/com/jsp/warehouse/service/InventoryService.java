package com.jsp.warehouse.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse.requestdto.InventoryRequest;
import com.jsp.warehouse.responsedto.InventoryResponse;
import com.jsp.warehouse.util.ResponseStructure;

public interface InventoryService {

	ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(InventoryRequest inventoryRequest, int storageId);

	ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,int productId);

	ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(int productId);

	ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventories();

}
