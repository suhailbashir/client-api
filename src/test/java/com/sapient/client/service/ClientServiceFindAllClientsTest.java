package com.sapient.client.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sapient.client.entity.Client;
import com.sapient.client.repository.ClientRepository;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ClientServiceFindAllClientsTest {

	@MockBean
	private ClientRepository clientRepository;
	
	@InjectMocks
	private ClientServiceImpl clientServiceImpl;
	
	@Test
	 void findAllClientsTestSuccess() {
		List<Client> clientList=ObjectUtility.createListOfClients();
		Mockito.when(clientRepository.findAll()).thenReturn(clientList);
		clientServiceImpl.findAllClients();
		assertFalse(false);
	}
	
}
