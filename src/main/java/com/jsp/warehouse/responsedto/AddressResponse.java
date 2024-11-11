package com.jsp.warehouse.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
	
	private String addressLine;
	private int addressId;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private String longitude;
	private String latitude;

}
