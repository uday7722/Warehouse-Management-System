package com.jsp.warehouse.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse.exception.AddressNotFoundByIdException;
import com.jsp.warehouse.exception.WareHouseNotFoundByIdException;
import com.jsp.warehouse.mapper.AddressMapper;
import com.jsp.warehouse.models.Address;
import com.jsp.warehouse.models.WareHouse;
import com.jsp.warehouse.repo.AddressRepo;
import com.jsp.warehouse.repo.WareHouseRepo;
import com.jsp.warehouse.requestdto.AddressRequest;
import com.jsp.warehouse.responsedto.AddressResponse;
import com.jsp.warehouse.service.AddressService;
import com.jsp.warehouse.util.ResponseStructure;


@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private WareHouseRepo wareHouseRepo;



	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest,int warehouseId) {

		WareHouse wareHouse = wareHouseRepo.findById(warehouseId).orElseThrow(()->new WareHouseNotFoundByIdException("No warehouse found by given id"));

		Address address = addressRepo.save(addressMapper.mapToAddress(addressRequest, new Address()));
		address.setWareHouse(wareHouse);
		wareHouseRepo.save(wareHouse);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AddressResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Warehouse Created")
				.setData(addressMapper.mapToAddressResponse(address)));

	}



	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(int addressId,AddressRequest addressRequest) {

		return addressRepo.findById(addressId).map(existingAddress->{
			existingAddress = addressMapper.mapToAddress(addressRequest, existingAddress);

			addressRepo.save(existingAddress);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<AddressResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Updated Successfully")
					.setData(addressMapper.mapToAddressResponse(existingAddress)));

		}).orElseThrow(()-> new AddressNotFoundByIdException("No address found by the given id"));




	}



	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(int addressId) {

		return addressRepo.findById(addressId).map(address->{

			return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<AddressResponse>()
					.setStatus(HttpStatus.FOUND.value())
					.setMessage("Address Found")
					.setData(addressMapper.mapToAddressResponse(address))
					);
		}).orElseThrow(()-> new AddressNotFoundByIdException("No address found by the given id"));



	}

}
