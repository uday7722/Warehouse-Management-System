package com.jsp.warehouse.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehouse.models.Address;
import com.jsp.warehouse.requestdto.AddressRequest;
import com.jsp.warehouse.responsedto.AddressResponse;

@Component
public class AddressMapper {

	public Address mapToAddress(AddressRequest addressRequest,Address address) {
		address.setAddressLine(addressRequest.getAddressLine());
		address.setCity(addressRequest.getCity());
		address.setCountry(addressRequest.getCountry());
		address.setState(addressRequest.getState());
		address.setPincode(addressRequest.getPincode());
		address.setLatitude(addressRequest.getLatitude());
		address.setLongitude(addressRequest.getLongitude());
		
		return address;
	}
	
	public AddressResponse mapToAddressResponse(Address address) {
		return AddressResponse.builder()
				.addressLine(address.getAddressLine())
				.addressId(address.getAddressId())
				.country(address.getCountry())
				.state(address.getState())
				.city(address.getCity())
				.pincode(address.getPincode())
				.longitude(address.getLongitude())
				.latitude(address.getLatitude())
				.build();
				
	}
}
