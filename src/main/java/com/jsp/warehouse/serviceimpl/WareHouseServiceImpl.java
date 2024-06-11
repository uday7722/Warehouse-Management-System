package com.jsp.warehouse.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.jsp.warehouse.mapper.WareHouseMapper;
import com.jsp.warehouse.models.WareHouse;
import com.jsp.warehouse.repo.WareHouseRepo;
import com.jsp.warehouse.requestdto.WareHouseRequest;
import com.jsp.warehouse.responsedto.WareHouseResponse;
import com.jsp.warehouse.service.WareHouseService;
import com.jsp.warehouse.util.ResponseStructure;

@Service
public class WareHouseServiceImpl implements WareHouseService{

	@Autowired
	private WareHouseRepo wareHouseRepo;

	@Autowired
	private WareHouseMapper wareHouseMapper;

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest request) {

		WareHouse wareHouse=wareHouseRepo.save(wareHouseMapper.mapToWarehouse(request, new WareHouse()));
		

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<WareHouseResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Warehouse Object created")
				.setData(wareHouseMapper.mapToWareHouseResponse(wareHouse)));		

	}

}
