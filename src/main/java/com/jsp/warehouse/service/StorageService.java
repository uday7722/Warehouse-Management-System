package com.jsp.warehouse.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse.models.StorageType;
import com.jsp.warehouse.requestdto.StorageRequest;
import com.jsp.warehouse.requestdto.StorageTypeRequest;
import com.jsp.warehouse.responsedto.AllStorageResponse;
import com.jsp.warehouse.responsedto.StorageResponse;
import com.jsp.warehouse.util.ResponseStructure;
import com.jsp.warehouse.util.SimpleStructure;

public interface StorageService {

	ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest, int warehouseId, int noOfStorageUnits, int storageTypeId);

	ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(int storageId, StorageRequest storageRequest);


}
