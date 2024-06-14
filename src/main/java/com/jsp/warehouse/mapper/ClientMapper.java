package com.jsp.warehouse.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehouse.models.Client;
import com.jsp.warehouse.requestdto.ClientRequest;
import com.jsp.warehouse.responsedto.ApiKeyResponse;
import com.jsp.warehouse.responsedto.ClientResponse;


@Component
public class ClientMapper {
	
	public Client mapToClientRequest(ClientRequest clientRequest,Client client) {
		client.setBuisnessName(clientRequest.getBuisnessName());
		client.setEmail(clientRequest.getEmail());
		client.setContactNumber(clientRequest.getContactNumber());
		
		return client;
	}
	
	public ApiKeyResponse mapToApiKey(Client client) {
		return ApiKeyResponse.builder()
				.apikey(client.getApiKey())
				.message("Created").build();
	}
	
	public ClientResponse mapToClientResponse(Client client) {
		return ClientResponse.builder()
				.clientId(client.getClientId())
				.buisnessName(client.getBuisnessName())
				.email(client.getEmail())
				.contactNumber(client.getContactNumber())
				.build();
	}
	
	

}
