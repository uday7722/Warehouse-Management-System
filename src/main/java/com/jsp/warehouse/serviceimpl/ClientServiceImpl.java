package com.jsp.warehouse.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse.exception.ClientNotFoundByIdException;
import com.jsp.warehouse.mapper.ClientMapper;
import com.jsp.warehouse.models.Client;
import com.jsp.warehouse.repo.ClientRepository;
import com.jsp.warehouse.requestdto.ClientRequest;
import com.jsp.warehouse.responsedto.ApiKeyResponse;
import com.jsp.warehouse.responsedto.ClientResponse;
import com.jsp.warehouse.service.ClientService;
import com.jsp.warehouse.util.ResponseStructure;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private ClientMapper clientMapper;
	

	@Override
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(ClientRequest clientRequset) {
		
		String apikey=UUID.randomUUID().toString();
		
		Client client = clientRepo.save(clientMapper.mapToClientRequest(clientRequset, new Client()));
		client.setApiKey(apikey);
		
		clientRepo.save(client);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<ApiKeyResponse>().setStatus(HttpStatus.CREATED.value()).setMessage("client data creeated").setData(clientMapper.mapToApiKey(client)));		
		
	}

	@Override
	public ResponseEntity<ResponseStructure<ClientResponse>> updateClients(ClientRequest clientRequest, int clientId) {
		
	return clientRepo.findById(clientId).map(existingClient->{
			clientMapper.mapToClientRequest(clientRequest, existingClient);
			
			existingClient=clientRepo.save(existingClient);
			
			return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<ClientResponse>().setStatus(HttpStatus.FOUND.value()).setMessage("Updated successfully").setData(clientMapper.mapToClientResponse(existingClient)));
			
		}).orElseThrow(()->new ClientNotFoundByIdException("No client found by the given id"));
	
		
		
	}

}
