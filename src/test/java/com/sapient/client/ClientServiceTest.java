

package com.sapient.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sapient.client.beans.AddressBean;
import com.sapient.client.beans.ClientBean;
import com.sapient.client.beans.EmiBean;
import com.sapient.client.beans.LoanBean;
import com.sapient.client.entity.Address;
import com.sapient.client.entity.Client;
import com.sapient.client.entity.EMI;
import com.sapient.client.entity.Loan;
import com.sapient.client.repository.ClientRepository;
import com.sapient.client.service.ClientService;

@TestMethodOrder(OrderAnnotation.class)
public class ClientServiceTest {

	@MockBean
	ClientRepository repository;

	@Autowired
	ClientService clientService;

	@Test
	@Order(1)
	@DisplayName("TESTING SAVE METHOD")
	public void testSaveClient() {
		System.out.println("save");
		Client client=createClient();
		when(repository.save(client)).thenReturn(client);
		assertEquals(client, clientService.saveClient(getClientbean()));
	}


	@Test
	@Order(3)
	@Disabled
	@DisplayName("TESTING UPDATE METHOD")
	public void testUpdateClient() {
		System.out.println("update");

	}

	@Test
	@Order(2)
	@DisplayName("TESTING GET METHOD")
	@Disabled
	public void testGetClients() {
		when(repository.findAll()).thenReturn(getClient());
		assertEquals(1, clientService.findAllClients());
	}
	
	@Test
	@Order(4)
	@Disabled
	@DisplayName("TESTING GET LOAN METHOD")
	public void testgetAllLoansOfClient() {
		System.out.println("loans");

	}

	@Test
	@Order(5)
	@Disabled
	@DisplayName("TESTING DELETE METHOD")
	public void testDeleteClient() {
		System.out.println("delete");

	}

	@AfterAll
	public static void clearAtEndOnce() {
		System.out.println("Clear at the end");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	private List<Client> getClient() {
		List<Client> listOClients = new ArrayList<>();
		Client client = new Client();
		client.setId(1l);
		client.setClientName("Suhail");

		List<Loan> listOfLoans = new ArrayList<>();

		Loan loan1 = new Loan();
		loan1.setId(4l);
		loan1.setLoanType("Saving");
		loan1.setLoanAccountNumber("LAN-12345-2021");

		List<EMI> emis = new ArrayList<>();
		EMI emi1 = new EMI();
		emi1.setId(5l);
		emi1.setDueDate(LocalDate.of(2018, 1, 1));
		emi1.setNumber(1l);
		emi1.setAmount(100.0);
		emi1.setLoan(loan1);

		EMI emi2 = new EMI();
		emi2.setId(6l);
		emi2.setDueDate(LocalDate.of(2018, 2, 1));
		emi2.setAmount(100.0);
		emi1.setNumber(2l);
		emi2.setLoan(loan1);
		
		emis.add(emi1);
		emis.add(emi2);
		
		loan1.setListOfEmis(emis);
		loan1.setClient(client);

		listOfLoans.add(loan1);

		List<Address> addresses = new ArrayList<>();

		Address addr1 = new Address();
		addr1.setId(2l);
		addr1.setCity("Jaipur");
		addr1.setState("Rajasthan");
		addr1.setCountry("India");
		addr1.setStreet("123");
		addr1.setZip("22223");
		addr1.setClient(client);

		Address addr2 = new Address();
		addr2.setId(3l);
		addr2.setCity("Srinagar");
		addr2.setState("JK");
		addr2.setCountry("India");
		addr2.setStreet("22");
		addr2.setZip("676767");
		addr2.setClient(client);

		addresses.add(addr1);
		addresses.add(addr2);

		client.setLoans(listOfLoans);
		client.setAddresses(addresses);

		listOClients.add(client);

		return listOClients;
	}

	

	private Client createClient () {
		
		Client client = new Client();
		client.setClientName("Suhail");

		List<Loan> listOfLoans = new ArrayList<>();

		Loan loan1 = new Loan();

		loan1.setLoanType("Saving");
		loan1.setLoanAccountNumber("LAN-12345-2021");

		List<EMI> emis = new ArrayList<>();
		EMI emi1 = new EMI();
		emi1.setDueDate(LocalDate.of(2018, 1, 1));
		emi1.setNumber(1l);
		emi1.setAmount(100.0);
		emi1.setLoan(loan1);

		EMI emi2 = new EMI();
		emi2.setDueDate(LocalDate.of(2018, 2, 1));
		emi2.setAmount(100.0);
		emi1.setNumber(2l);
		emi2.setLoan(loan1);
		
		emis.add(emi1);
		emis.add(emi2);
		
		loan1.setListOfEmis(emis);
		loan1.setClient(client);

		listOfLoans.add(loan1);
		System.out.println("Client"+listOfLoans);
		List<Address> addresses = new ArrayList<>();

		Address addr1 = new Address();
		addr1.setCity("Jaipur");
		addr1.setState("Rajasthan");
		addr1.setCountry("India");
		addr1.setStreet("123");
		addr1.setZip("22223");
		addr1.setClient(client);

		Address addr2 = new Address();

		addr2.setCity("Srinagar");
		addr2.setState("JK");
		addr2.setCountry("India");
		addr2.setStreet("22");
		addr2.setZip("676767");
		addr2.setClient(client);

		addresses.add(addr1);
		addresses.add(addr2);

		client.setLoans(listOfLoans);
		client.setAddresses(addresses);
       
		System.out.println("Client"+client);
		 
		return client;
	}
	

	private ClientBean getClientbean() {
		ClientBean bean=new ClientBean();
		bean.setId(1l);
		bean.setClientName("Suhail");

		List<LoanBean> listOfLoans = new ArrayList<>();

		LoanBean loan1 = new LoanBean();
		loan1.setId(4l);
		loan1.setLoanType("Saving");
		loan1.setLoanAccountNumber("LAN-12345-2021");

		List<EmiBean> emis = new ArrayList<>();
		EmiBean emi1 = new EmiBean();
		emi1.setId(5l);
		emi1.setDueDate(LocalDate.of(2018, 1, 1));
		emi1.setNumber(1l);
		emi1.setAmount(100.0);
		

		EmiBean emi2 = new EmiBean();
		emi2.setId(6l);
		emi2.setDueDate(LocalDate.of(2018, 2, 1));
		emi2.setAmount(100.0);
		emi1.setNumber(2l);
		
		emis.add(emi1);
		emis.add(emi2);
		
		loan1.setListOfEmis(emis);
		

		listOfLoans.add(loan1);

		List<AddressBean> addresses = new ArrayList<>();

		AddressBean addr1 = new AddressBean ();
		addr1.setId(2l);
		addr1.setCity("Jaipur");
		addr1.setState("Rajasthan");
		addr1.setCountry("India");
		addr1.setStreet("123");
		addr1.setZip("22223");
		

		AddressBean addr2 = new AddressBean();
		addr2.setId(3l);
		addr2.setCity("Srinagar");
		addr2.setState("JK");
		addr2.setCountry("India");
		addr2.setStreet("22");
		addr2.setZip("676767");
		

		addresses.add(addr1);
		addresses.add(addr2);

		bean.setLoans(listOfLoans);
		bean.setAddresses(addresses);
		return bean;
	}

}
