package com.sapient.client.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.sapient.client.beans.AddressBean;
import com.sapient.client.beans.ClientBean;
import com.sapient.client.beans.EmiBean;
import com.sapient.client.beans.LoanBean;
import com.sapient.client.entity.Address;
import com.sapient.client.entity.Client;
import com.sapient.client.entity.EMI;
import com.sapient.client.entity.Loan;
import com.sapient.client.repository.ClientRepository;

public class ClientServiceTest {


	@Test
	public void saveClientTest() {

		Client client = createClient();
		
		ClientRepository clientRepository = mock(ClientRepository.class);
		
		ClientBean clientBean = createClientBean();
		ArgumentCaptor<ArrayList> captor = ArgumentCaptor.forClass(ArrayList.class);
		ClientServiceImplFortesting target = spy(new ClientServiceImplFortesting());
		doNothing().when(target).transformClientBeanIntoClient(Mockito.eq(clientBean), captor.capture(),captor.capture());
		
		ReflectionTestUtils.setField(target, "clientRepository", clientRepository);
		when(clientRepository.save(client)).thenReturn(client);
		
		target.saveClient(clientBean);
	}
	
	class ClientServiceImplFortesting extends ClientServiceImpl{
		@Override
		protected void transformClientIntoClientBean(Client client, List<LoanBean> listOfLoanBeans, List<AddressBean> addressesBeans) {
			
		}
	}

	private Client createClient() {

		EMI emi = EMI.builder().id(5L).number(1L).amount(100.0).dueDate(LocalDate.now()).build();

		List<EMI> emis = Arrays.asList(emi);

		Loan loan = Loan.builder().id(4L).loanAccountNumber("123_LAN_222").loanType("Saving").listOfEmis(emis).build();

		List<Loan> loans = Arrays.asList(loan);

		Address a1 = Address.builder().id(2L).street("124").State("Rajasthan").Country("India").zip("67676").build();

		Address a2 = Address.builder().id(3L).city("Srinagar").street("12").State("JK").Country("India").zip("8888")
				.build();

		List<Address> addresses = Arrays.asList(a1, a2);

		return Client.builder().id(1L).clientName("Suhail").loans(loans).addresses(addresses).build();
	}
	
	private ClientBean createClientBean() {

		EmiBean emiBean = EmiBean.builder().id(5L).number(1L).amount(100.0).dueDate(LocalDate.now()).build();

		List<EmiBean> emiBeans = Arrays.asList(emiBean);

		LoanBean loanBean = LoanBean.builder().id(4L).loanAccountNumber("123_LAN_222").loanType("Saving")
				.listOfEmis(emiBeans).build();

		List<LoanBean> loanBeans = Arrays.asList(loanBean);

		AddressBean a1 = AddressBean.builder().id(2L).street("124").State("Rajasthan").Country("India").zip("67676")
				.build();

		AddressBean a2 = AddressBean.builder().id(3L).city("Srinagar").street("12").State("JK").Country("India")
				.zip("8888").build();

		List<AddressBean> addressBeans = Arrays.asList(a1, a2);

		return ClientBean.builder().id(1L).clientName("Suhail").loans(loanBeans).addresses(addressBeans).build();
	}
}
