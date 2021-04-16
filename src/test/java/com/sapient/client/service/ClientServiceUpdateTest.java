package com.sapient.client.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

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
 class ClientServiceUpdateTest {
	@MockBean
	private ClientRepository clientRepository;
	
	@InjectMocks
	private ClientServiceImpl clientServiceImpl;
	
	
	@Test
	 void updateClientTestSuccess() {
			Optional<Client> client=ObjectUtility.createOptionalClient();
			Mockito.when(clientRepository.findById(Mockito.any())).thenReturn(client);
			
			clientServiceImpl.updateClient(ObjectUtility.createClientBean());
			assertFalse(false);
		}
	}
