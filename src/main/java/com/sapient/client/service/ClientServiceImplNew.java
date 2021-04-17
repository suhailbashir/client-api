package com.sapient.client.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sapient.client.dto.ClientDto;
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
		Client client = mapper.mapClientDtoToClient(clientDto);
		client = clientRepository.save(client);
		return mapper.mapClientToClientDto(client);
	}

	@Override
	public ClientDto findClientById(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		ClientDto clientDto = ClientDto.builder().build();
		if (client.isPresent()) {
			clientDto = mapper.mapClientToClientDto(client.get());
		}
		return clientDto;
	}

	@Override
	public List<ClientDto> findAllClients() {
		List<Client> listOfClients = clientRepository.findAll();
		return mapper.mapToClientDtos(listOfClients);
	}

	@Override
	public ClientDto updateClient(ClientDto clientDto, Long id) {
		Client client = clientRepository.findById(id).get();
		Client client2=mapper.mapClientDtoToClient(clientDto);
	if(client!=null) {
		clientRepository.save(client2);
	}
			
		return clientDto;	
		
	}

	@Override
	public List<ClientDto> deleteClientById(Long id) {
		clientRepository.deleteById(id);
		return findAllClients();
	}

}
