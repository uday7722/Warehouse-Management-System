package com.jsp.warehouse.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse.requestdto.AddressRequest;
import com.jsp.warehouse.responsedto.AddressResponse;
import com.jsp.warehouse.util.ResponseStructure;

import jakarta.validation.Valid;

public interface AddressService {

	ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@Valid AddressRequest addressRequest, int warehouseId);

	ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(int addressId, AddressRequest addressRequest);

	ResponseEntity<ResponseStructure<AddressResponse>> findAddress(int addressId);

}
