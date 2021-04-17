package com.sapient.client.service;

import java.util.List;

import com.sapient.client.dto.ClientDto;

public interface ClientService {
	
	public ClientDto saveClient(ClientDto clientBean);
	public ClientDto findClientById(Long clientBean);
	public List<ClientDto> findAllClients();
	public ClientDto updateClient(ClientDto clientBean,Long id);
	public List<ClientDto>deleteClientById(Long id);
}
