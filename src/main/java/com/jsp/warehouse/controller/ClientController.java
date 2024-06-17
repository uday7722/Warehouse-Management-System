package com.jsp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse.requestdto.ClientRequest;
import com.jsp.warehouse.responsedto.ApiKeyResponse;
import com.jsp.warehouse.responsedto.ClientResponse;
import com.jsp.warehouse.service.ClientService;
import com.jsp.warehouse.util.ResponseStructure;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/client/register")
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@RequestBody ClientRequest clientRequset) {
		
		return clientService.registerClient(clientRequset);
	}
	
	@PutMapping("/client/{clientId}/clients")
	public ResponseEntity<ResponseStructure<ClientResponse>> updateClients(@PathVariable int clientId, @RequestBody ClientRequest clientRequest) {
		
		return clientService.updateClients(clientRequest,clientId);
	}

}
