package com.jsp.warehouse.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.warehouse.models.Address;
import com.jsp.warehouse.models.WareHouse;
import com.jsp.warehouse.requestdto.WareHouseRequest;
import com.jsp.warehouse.responsedto.WareHouseResponse;

@Component
public class WareHouseMapper {

	public WareHouse mapToWarehouse(WareHouseRequest wareHouseRequest,WareHouse wareHouse) {
		wareHouse.setName(wareHouseRequest.getName());
		return wareHouse;


	}
	
	public WareHouseResponse mapToWareHouseResponse(WareHouse wareHouse) {
		return WareHouseResponse.builder()
				.wareHouseId(wareHouse.getWareHouseId())
				.name(wareHouse.getName())
				.totalCapacityInKg(wareHouse.getTotalCapacityInKg())
				.build();
	}
	
	@Autowired
	private AddressMapper addressMapper;
	
	public WareHouseResponse mapToWareHouseAddresses(Address address,WareHouse house) {
		WareHouseResponse wareHouseResponse = mapToWareHouseResponse(house);
		wareHouseResponse.setAddressResponse(addressMapper.mapToAddressResponse(address));
		
		return wareHouseResponse;
		
		
		
		
	}
	

}
