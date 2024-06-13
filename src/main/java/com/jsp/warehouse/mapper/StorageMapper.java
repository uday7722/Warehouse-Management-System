package com.jsp.warehouse.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehouse.models.Storage;
import com.jsp.warehouse.requestdto.StorageRequest;
import com.jsp.warehouse.responsedto.StorageResponse;

@Component
public class StorageMapper {

	public Storage mapToStorage(StorageRequest  storageRequest,Storage storage) {
		storage.setBlockName(storageRequest.getBlockName());
		storage.setSection(storageRequest.getSection());
		storage.setCapacityWeightInKg(storageRequest.getCapacityWeightInKg());
		storage.setLengthInMeters(storageRequest.getLengthInMeters());
		storage.setBreadthInMeters(storageRequest.getBreadthInMeters());
		storage.setHeightInMeters(storageRequest.getHeightInMeters());
		storage.setMaterialType(storageRequest.getMaterialType());

		return storage;
	}

	public StorageResponse mapToStorageResponse(Storage storage) {
		return StorageResponse.builder()
				.storageId(storage.getStorageId())
				.blockName(storage.getBlockName())
				.section(storage.getSection())
				.availableAreaInMetres(storage.getAvailableAreaInMetres())
				.capacityWeightInKg(storage.getCapacityWeightInKg())
				.materialTypes(storage.getMaterialType())
				.build();

	}


}
