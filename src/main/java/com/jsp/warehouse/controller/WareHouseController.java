package com.jsp.warehouse.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse.requestdto.WareHouseRequest;
import com.jsp.warehouse.service.WareHouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1")
public class WareHouseController {
	
	@Autowired
	private WareHouseService service;
	
	@PostMapping("/wareHouses")
	public String saveWareHouse(@RequestBody WareHouseRequest request) {
	
		return "Ware house created"; 
		
	}
	

}
