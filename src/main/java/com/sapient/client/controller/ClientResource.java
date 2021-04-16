package com.sapient.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.client.beans.ClientDto;
import com.sapient.client.service.ClientService;

@RestController
@RequestMapping("/client-api")
public class ClientResource {

	@Autowired
	ClientService clientService;
	
	
	@PostMapping("/client")
	public ResponseEntity<ClientDto> saveClient(@RequestBody ClientDto bean){
		ClientDto clientBean=clientService.saveClient(bean);
		return  ResponseEntity.status(HttpStatus.CREATED).body(clientBean);
	}
	
/*	@GetMapping("/client/{id}")
	public ResponseEntity<ClientDto>getClientById(@PathVariable(name = "id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientById(id));
	}
	
	@GetMapping("/client")
	public ResponseEntity<List<ClientDto>>getAllClients(){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findAllClients());
	}
	
	@PutMapping("/client")
	public ResponseEntity<ClientDto>updateClient(@RequestBody ClientDto bean){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClient(bean));
	}
	
	@DeleteMapping("/client/{id}")
	public ResponseEntity<List<ClientDto>>deleteClient(@PathVariable(name = "id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.deleteClientById(id));
	}
	*/
}

