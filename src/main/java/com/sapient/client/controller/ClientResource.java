package com.sapient.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.client.beans.ClientBean;
import com.sapient.client.service.ClientService;

@RestController
@RequestMapping("/client-api")
public class ClientResource {

	@Autowired
	ClientService clientService;
	
	
	@PostMapping("/client")
	public ResponseEntity<ClientBean> saveClient(@RequestBody ClientBean bean){
		ClientBean clientBean=clientService.saveClient(bean);
		return  ResponseEntity.status(HttpStatus.CREATED).body(clientBean);
	}
	
	@GetMapping("/client/{id}")
	public ResponseEntity<ClientBean>getClientById(@PathVariable(name = "id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientById(id));
	}
	
	@GetMapping("/client")
	public ResponseEntity<List<ClientBean>>getAllClients(){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findAllClients());
	}
	
	@PutMapping("/client")
	public ResponseEntity<ClientBean>updateClient(@RequestBody ClientBean bean){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClient(bean));
	}
	
	@DeleteMapping("/client/{id}")
	public ResponseEntity<List<ClientBean>>deleteClient(@PathVariable(name = "id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.deleteClientById(id));
	}
	
}

