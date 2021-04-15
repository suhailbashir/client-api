package com.sapient.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.Module.SetupContext;
import com.sapient.client.beans.AddressBean;
import com.sapient.client.beans.ClientBean;
import com.sapient.client.beans.EmiBean;
import com.sapient.client.beans.LoanBean;
import com.sapient.client.controller.ClientResource;
import com.sapient.client.service.ClientService;


public class ClientResourceTest {

	@Mock
	ClientService clientService;
	
	@InjectMocks
	ClientResource clientResource;
	
	@BeforeEach
	void setup() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Order(1)
	@DisplayName("TESTING SAVE METHOD")
	public void saveClient() {
		
		ClientBean clientBean=createClientBean();
		when(clientService.saveClient(clientBean)).thenReturn(clientBean);
		assertEquals(clientBean, clientService.saveClient(clientBean));
	}

	private ClientBean createClientBean() {
		
		EmiBean emiBean=EmiBean.builder()
								.id(5L)
								.number(1L).amount(100.0)
								.dueDate(LocalDate.now())
								.build();
		
		List<EmiBean>emiBeans=Arrays.asList(emiBean);
		
		LoanBean loanBean=LoanBean.builder()
								  .id(4L).loanAccountNumber("123_LAN_222")
								  .loanType("Saving")
								  .listOfEmis(emiBeans)
								  .build();
		
		List<LoanBean>loanBeans=Arrays.asList(loanBean);
		
		AddressBean a1=	AddressBean.builder()
								   .id(2L)
								   .street("124")
								   .State("Rajasthan")
								   .Country("India")
								   .zip("67676")
								   .build();
		
		AddressBean a2=	AddressBean.builder()
								   .id(3L)
								   .city("Srinagar")
								   .street("12")
								   .State("JK")
								   .Country("India")
								   .zip("8888")
								   .build();
		
		List<AddressBean>addressBeans=Arrays.asList(a1,a2);
		
		return ClientBean.builder().id(1L).clientName("Suhail").loans(loanBeans).addresses(addressBeans).build();
	}
	
}
