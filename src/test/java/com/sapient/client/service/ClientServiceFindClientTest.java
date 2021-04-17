package com.sapient.client.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.sapient.client.dto.ClientDto;
import com.sapient.client.entity.Client;
import com.sapient.client.repository.ClientRepository;

public class ClientServiceFindClientTest {

	
	private ClientRepository clientRepository=Mockito.mock(ClientRepository.class);
	
	@Test
	@DisplayName("Should Find Client By ID")
	void shouldFindClientById() {
		ClientService service=new ClientServiceImpl();
		Client client=ObjectUtility.createClient();
		
		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
		
		ClientDto clientBean=ObjectUtility.createClientBean();

		
		
	}
	
	
}
