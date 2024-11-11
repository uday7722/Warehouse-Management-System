package com.jsp.warehouse.mapper;

import org.springframework.stereotype.Component;
import com.jsp.warehouse.models.StorageType;
import com.jsp.warehouse.requestdto.StorageTypeRequest;
import com.jsp.warehouse.responsedto.StorageTypeResponse;

@Component
public class StorageTypeMapper {
	
	public StorageType mapToStorageRequest(StorageTypeRequest storageTypeRequest,StorageType storageType) {
		storageType.setLengthInMeters(storageTypeRequest.getLengthInMeters());
		storageType.setBreadthInMeters(storageTypeRequest.getBreadthInMeters());
		storageType.setHeightInMeters(storageTypeRequest.getHeightInMeters());
		storageType.setCapacityWeightInKg(storageTypeRequest.getCapacityWeightInKg());
		
		return storageType;
	}
	
	public StorageTypeResponse mapToStorageTypeResonse(StorageType storageType) {
		return StorageTypeResponse.builder()
				.storageTypeId(storageType.getStorageTypeId())
				.lengthInMeters(storageType.getLengthInMeters())
				.breadthInMeters(storageType.getBreadthInMeters())
				.heightInMeters(storageType.getHeightInMeters())
				.capacityWeightInKg(storageType.getCapacityWeightInKg())
				.unitsAvailable(storageType.getUnitsAvailable())
				.build();
	}

}
