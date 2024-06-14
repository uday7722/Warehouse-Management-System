package com.jsp.warehouse.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse.requestdto.ClientRequest;
import com.jsp.warehouse.responsedto.ApiKeyResponse;
import com.jsp.warehouse.responsedto.ClientResponse;
import com.jsp.warehouse.util.ResponseStructure;


public interface ClientService {

	ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(ClientRequest clientRequset);
	
	ResponseEntity<ResponseStructure<ClientResponse>> updateClients(ClientRequest clientRequest, int clientId);
	
	

}
