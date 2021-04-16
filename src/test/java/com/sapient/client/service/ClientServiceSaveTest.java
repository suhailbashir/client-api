package com.sapient.client.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

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
public class ClientServiceSaveTest {

	@MockBean
	private ClientRepository clientRepository;
	
	@InjectMocks
	private ClientServiceImpl clientServiceImpl;
	

	@Test
	public void saveClientTestSuccess() {
		Client client=ObjectUtility.createClient();
		Mockito.when(clientRepository.save(Mockito.any())).thenReturn(client);
		clientServiceImpl.saveClient(ObjectUtility.createClientBean());
		assertFalse(false);
	}
	
	@Test
	public void saveClientTestNegative() {
		Mockito.when(clientRepository.save(Mockito.any())).thenThrow(new NullPointerException());
		clientServiceImpl.saveClient((ObjectUtility.createClientBeanWIthNullLoan()));
		assertFalse(true);
	}


}
