package com.jsp.warehouse.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehouse.models.Inventory;
import com.jsp.warehouse.requestdto.InventoryRequest;
import com.jsp.warehouse.responsedto.InventoryResponse;

@Component
public class InventoryMapper {
	

	public Inventory mapToInventory(InventoryRequest inventoryRequest,Inventory inventory) {
		inventory.setProductTitle(inventoryRequest.getProductTitle());
		inventory.setLengthInMeters(inventoryRequest.getLengthInMeters());
		inventory.setBreadthInMeters(inventoryRequest.getBreadthInMeters());
		inventory.setHeightInMeters(inventoryRequest.getHeightInMeters());
		inventory.setQuantity(inventoryRequest.getQuantity());
		inventory.setWeightInKg(inventoryRequest.getWeightInKg());
		inventory.setMaterialTypes(inventoryRequest.getMaterialTypes());
		inventory.setSellerId(inventoryRequest.getSellerId());
		
		return inventory;
	
	}
	
	public InventoryResponse  mapToInventoryResponse(Inventory inventory) {
		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.productTitle(inventory.getProductTitle())
				.weightInKg(inventory.getWeightInKg())
				.materialTypes(inventory.getMaterialTypes())
				.restockedAt(inventory.getRestockedAt())
				.sellerId(inventory.getSellerId())
				.quantity(inventory.getQuantity())
				.build();
	}
	
}
