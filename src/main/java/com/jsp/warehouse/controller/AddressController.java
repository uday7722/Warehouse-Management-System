package com.jsp.warehouse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse.requestdto.AddressRequest;
import com.jsp.warehouse.responsedto.AddressResponse;
import com.jsp.warehouse.service.AddressService;
import com.jsp.warehouse.util.ResponseStructure;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/v1")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PreAuthorize("hasAuthority('CREATE_ADDRESS')")
	@PostMapping("/warehouses/{warehouseId}/addresses")
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@RequestBody @Valid AddressRequest addressRequest,@PathVariable int warehouseId) {
		return addressService.addAddress(addressRequest,warehouseId);
		
	}
	
	@PreAuthorize("hasAuthority('UPDATE_ADDRESS')")
	@PutMapping("{addressId}/addresses")
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@PathVariable int addressId, @RequestBody AddressRequest addressRequest) {
		return addressService.updateAddress(addressId,addressRequest);
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping("/{addressId}/addresses")
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(@PathVariable int addressId) {
		return addressService.findAddress(addressId);
	}
	
	

}
