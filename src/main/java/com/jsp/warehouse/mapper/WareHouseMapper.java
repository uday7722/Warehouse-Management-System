package com.jsp.warehouse.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehouse.models.WareHouse;
import com.jsp.warehouse.requestdto.WareHouseRequest;
import com.jsp.warehouse.responsedto.WareHouseResponse;

@Component
public class WareHouseMapper {

	public WareHouse mapToWarehouse(WareHouseRequest wareHouseRequest,WareHouse wareHouse) {
		wareHouse.setName(wareHouseRequest.getName());
		wareHouse.setTotalCapacityInKg(wareHouseRequest.getTotalCapacityInKg());
		return wareHouse;


	}
	
	public WareHouseResponse mapToWareHouseResponse(WareHouse wareHouse) {
		return WareHouseResponse.builder()
				.wareHouseId(wareHouse.getWareHouseId())
				.name(wareHouse.getName())
				.totalCapacityInKg(wareHouse.getTotalCapacityInKg())
				.build();
	}

}
