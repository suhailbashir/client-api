package com.sapient.client.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.aspectj.apache.bcel.generic.InstructionConstants.Clinit;

import com.sapient.client.beans.AddressDto;
import com.sapient.client.beans.ClientDto;
import com.sapient.client.beans.EmiDto;
import com.sapient.client.beans.LoanDto;
import com.sapient.client.entity.Address;
import com.sapient.client.entity.Client;
import com.sapient.client.entity.EMI;
import com.sapient.client.entity.Loan;

public class ObjectUtility {

	public  static Client createClient() {

		EMI emi = EMI.builder().id(5L).number(1L).amount(100.0).dueDate(LocalDate.now()).build();
		List<EMI> emis = Arrays.asList(emi);
		Loan loan = Loan.builder().id(4L).loanAccountNumber("123_LAN_222").loanType("Saving").listOfEmis(emis).build();
		List<Loan> loans = Arrays.asList(loan);
		Address a1 = Address.builder().id(2L).street("124").state("Rajasthan").country("India").zip("67676").build();
		Address a2 = Address.builder().id(3L).city("Srinagar").street("12").state("JK").country("India").zip("8888").build();
		List<Address> addresses = Arrays.asList(a1, a2);
		return Client.builder().id(1L).clientName("Suhail").loans(loans).addresses(addresses).build();
	}
	
	public  static ClientDto createClientBean() {

		EmiDto emiBean = EmiDto.builder().id(5L).number(1L).amount(100.0).dueDate(LocalDate.now()).build();
		List<EmiDto> emiBeans = Arrays.asList(emiBean);
		LoanDto loanBean = LoanDto.builder().id(4L).loanAccountNumber("123_LAN_222").loanType("Saving").listOfEmis(emiBeans).build();
		List<LoanDto> loanBeans = Arrays.asList(loanBean);
		AddressDto a1 = AddressDto.builder().id(2L).street("124").state("Rajasthan").country("India").zip("67676").build();
		AddressDto a2 = AddressDto.builder().id(3L).city("Srinagar").street("12").state("JK").country("India").zip("8888").build();
		List<AddressDto> addressBeans = Arrays.asList(a1, a2);
		return ClientDto.builder().id(1L).clientName("Suhail").loans(loanBeans).addresses(addressBeans).build();
	}
	
	
	public  static Client createClientWIthNullLoan() {

		Address a1 = Address.builder().id(2L).street("124").state("Rajasthan").country("India").zip("67676").build();
		Address a2 = Address.builder().id(3L).city("Srinagar").street("12").state("JK").country("India").zip("8888").build();
		List<Address> addresses = Arrays.asList(a1, a2);
		return Client.builder().id(1L).clientName("Suhail").addresses(addresses).build();
	}
	
	public  static ClientDto createClientBeanWIthNullLoan() {

		AddressDto a1 = AddressDto.builder().id(2L).street("124").state("Rajasthan").country("India").zip("67676").build();
		AddressDto a2 = AddressDto.builder().id(3L).city("Srinagar").street("12").state("JK").country("India").zip("8888").build();
		List<AddressDto> addressBeans = Arrays.asList(a1, a2);
		return ClientDto.builder().id(1L).clientName("Suhail").addresses(addressBeans).build();
	}
	
	public static List<Client> createListOfClients(){
		List<Client>listOfClientBeans=new ArrayList<>();

		EMI emi = EMI.builder().id(5L).number(1L).amount(100.0).dueDate(LocalDate.now()).build();
		List<EMI> emis = Arrays.asList(emi);
		Loan loan = Loan.builder().id(4L).loanAccountNumber("123_LAN_222").loanType("Saving").listOfEmis(emis).build();
		List<Loan> loans = Arrays.asList(loan);
		Address a1 = Address.builder().id(2L).street("124").state("Rajasthan").country("India").zip("67676").build();
		Address a2 = Address.builder().id(3L).city("Srinagar").street("12").state("JK").country("India").zip("8888").build();
		List<Address> addresses = Arrays.asList(a1, a2);
		Client client= Client.builder().id(1L).clientName("Suhail").loans(loans).addresses(addresses).build();
		listOfClientBeans.add(client);
		return listOfClientBeans;
		
	}
	
	public static List<ClientDto> createListOfClientBean(){
		List<ClientDto>listOfClientBeans=new ArrayList<>();

		EmiDto emiBean = EmiDto.builder().id(5L).number(1L).amount(100.0).dueDate(LocalDate.now()).build();
		List<EmiDto> emiBeans = Arrays.asList(emiBean);
		LoanDto loanBean = LoanDto.builder().id(4L).loanAccountNumber("123_LAN_222").loanType("Saving").listOfEmis(emiBeans).build();
		List<LoanDto> loanBeans = Arrays.asList(loanBean);
		AddressDto a1 = AddressDto.builder().id(2L).street("124").state("Rajasthan").country("India").zip("67676").build();
		AddressDto a2 = AddressDto.builder().id(3L).city("Srinagar").street("12").state("JK").country("India").zip("8888").build();
		List<AddressDto> addressBeans = Arrays.asList(a1, a2);
		listOfClientBeans.add( ClientDto.builder().id(1L).clientName("Suhail").loans(loanBeans).addresses(addressBeans).build());
		return listOfClientBeans;
		
	}
	
	public static Optional<Client>  createOptionalClient(){
		
		EMI emi = EMI.builder().id(5L).number(1L).amount(100.0).dueDate(LocalDate.now()).build();
		List<EMI> emis = Arrays.asList(emi);
		Loan loan = Loan.builder().id(4L).loanAccountNumber("123_LAN_222").loanType("Saving").listOfEmis(emis).build();
		List<Loan> loans = Arrays.asList(loan);
		Address a1 = Address.builder().id(2L).street("124").state("Rajasthan").country("India").zip("67676").build();
		Address a2 = Address.builder().id(3L).city("Srinagar").street("12").state("JK").country("India").zip("8888").build();
		List<Address> addresses = Arrays.asList(a1, a2);
		Client client= Client.builder().id(1L).clientName("Suhail").loans(loans).addresses(addresses).build();
		
		return Optional.of(client);
		
	}
	
}
