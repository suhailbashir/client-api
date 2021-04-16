package com.sapient.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sapient.client.beans.ClientDto;
import com.sapient.client.entity.Client;
import com.sapient.client.mapper.ClientMapper;
import com.sapient.client.repository.ClientRepository;
@Service
@Primary
public class ClientServiceImplNew implements ClientService {

	
	@Autowired
	ClientMapper mapper;
	
	@Autowired
	ClientRepository clientRepository;
	
	
	@Override
	public ClientDto saveClient(ClientDto clientDto) {
		Client client= mapper.mapClientDtoToClient(clientDto);
		client=clientRepository.save(client);
		return mapper.mapClientToClientDto(client);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public ClientDto findClientById(Long clientBean) {
	
		return null;
	}

	@Override
	public List<ClientDto> findAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDto updateClient(ClientDto clientBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientDto> deleteClientById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
