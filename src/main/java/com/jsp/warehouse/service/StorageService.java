package com.jsp.warehouse.service;


import org.springframework.http.ResponseEntity;

import com.jsp.warehouse.requestdto.StorageRequest;
import com.jsp.warehouse.responsedto.StorageResponse;
import com.jsp.warehouse.util.ResponseStructure;
import com.jsp.warehouse.util.SimpleStructure;

public interface StorageService {

	ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest, int warehouseId, int noOfStorageUnits);

	ResponseEntity<ResponseStructure<StorageResponse>> updateWarehouse(int storageId, StorageRequest storageRequest);

}
