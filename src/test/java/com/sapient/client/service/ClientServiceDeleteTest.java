package com.sapient.client.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sapient.client.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ClientServiceDeleteTest {
	@MockBean
	private ClientRepository clientRepository;

	@InjectMocks
	private ClientServiceImpl clientServiceImpl;

	@Test
	public void deleteClientTestSuccess() {

		Long id = 1L;
		clientServiceImpl.deleteClientById(id);
		verify(clientRepository, times(1)).deleteById(id);
	}

}
