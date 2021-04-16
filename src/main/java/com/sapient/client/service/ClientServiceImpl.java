package com.sapient.client.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.client.beans.AddressDto;
import com.sapient.client.beans.ClientDto;
import com.sapient.client.beans.EmiDto;
import com.sapient.client.beans.LoanDto;
import com.sapient.client.entity.Address;
import com.sapient.client.entity.Client;
import com.sapient.client.entity.EMI;
import com.sapient.client.entity.Loan;
import com.sapient.client.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Override
	public ClientDto saveClient(ClientDto clientBean) {

		List<Loan> listOfLoans = new ArrayList<>();
		List<Address> addresses = new ArrayList<>();
		List<LoanDto> listOfLoanBeans = new ArrayList<>();
		List<AddressDto> addressesBeans = new ArrayList<>();
		
		transformClientBeanIntoClient(clientBean, listOfLoans, addresses);
		Client client= Client.builder().clientName(clientBean.getClientName()).build();
		
		addresses.forEach(address->{
			address.setClient(client);
		});
		
		listOfLoans.stream().forEach(loan->{
			loan.setClient(client);
		});
		
		client.setLoans(listOfLoans);
		client.setAddresses(addresses);
		
		Client savedClient = clientRepository.save(client);
		
		transformClientIntoClientBean(savedClient, listOfLoanBeans, addressesBeans);
		
		return ClientDto.builder().id(savedClient.getId()).clientName(clientBean.getClientName()).loans(listOfLoanBeans).addresses(addressesBeans).build();
	}

	@Override
	public ClientDto findClientById(Long clientId) {
		
			Optional<Client> client = clientRepository.findById(clientId);
			List<LoanDto> listOfLoanBeans = new ArrayList<>();
			List<AddressDto> addressesBeans = new ArrayList<>();
			
			if (client.isPresent()) {
				transformClientIntoClientBean(client.get(), listOfLoanBeans, addressesBeans);
			
			}
		return ClientDto.builder().id(client.get().getId()).clientName(client.get().getClientName()).loans(listOfLoanBeans).addresses(addressesBeans).build();
	}

	@Override
	public List<ClientDto> findAllClients() {
		log.info("ClientServiceImpl::findAllClients::");
		List<Client>listOfClients=clientRepository.findAll();
		List<ClientDto>listOfClientBeans=new ArrayList<>();
		
		listOfClients.stream().forEach(client->{
			
			List<LoanDto> listOfLoanBeans = new ArrayList<>();
			List<AddressDto> addressesBeans = new ArrayList<>();;
			transformClientIntoClientBean(client, listOfLoanBeans, addressesBeans);

			listOfClientBeans.add(ClientDto.builder().id(client.getId()).clientName(client.getClientName()).loans(listOfLoanBeans).addresses(addressesBeans).build());
		}
		
		);
		return listOfClientBeans;
		
	}

	@Override
	public ClientDto updateClient(ClientDto clientBean) {
		
		Optional<Client> client = clientRepository.findById(clientBean.getId());
		List<Loan> listOfLoans = new ArrayList<>();
		List<Address> addresses = new ArrayList<>();
		List<LoanDto> listOfLoanBeans = new ArrayList<>();
		List<AddressDto> addressesBeans = new ArrayList<>();
		ClientDto bean=null;
		
		if (client.isPresent()) {
			transformClientBeanIntoClient(clientBean, listOfLoans, addresses);
			
			Client clientNew= Client.builder().id(clientBean.getId()).clientName(clientBean.getClientName()).build();
			
			listOfLoans.stream().forEach(loan->{
				loan.setClient(clientNew);
			});
			
			addresses.forEach(address->{
				address.setClient(clientNew);
			});
			
			clientNew.setLoans(listOfLoans);
			clientNew.setAddresses(addresses);
			
			Client	updatedClient = clientRepository.save(clientNew);
			
			transformClientIntoClientBean(updatedClient, listOfLoanBeans, addressesBeans);
			bean=ClientDto.builder().id(updatedClient.getId()).clientName(clientBean.getClientName()).loans(listOfLoanBeans).addresses(addressesBeans).build();
		}else {
			throw new RuntimeException("Client with such id doesn't exist");
		}
		return bean;
	}
	
	@Override
	public List<ClientDto> deleteClientById(Long id) {
	
		clientRepository.deleteById(id);
		return findAllClients();
	}
	
	protected void transformClientIntoClientBean(Client client, List<LoanDto> listOfLoanBeans, List<AddressDto> addressesBeans) {
		
		
		client.getLoans().forEach(loan -> {
			List<EmiDto> emiBeans = new ArrayList<>();
			loan.getListOfEmis().stream().forEach(emi -> {
				EmiDto emiBean = EmiDto.builder().id(emi.getId()).amount(emi.getAmount()).dueDate(emi.getDueDate()).number(emi.getNumber()).build();
				emiBeans.add(emiBean);
			});

			LoanDto loanBean = LoanDto.builder().id(loan.getId()).loanAccountNumber(loan.getLoanAccountNumber()).loanType(loan.getLoanType()).listOfEmis(emiBeans).build();
			listOfLoanBeans.add(loanBean);
		});

		client.getAddresses().forEach(address -> {
			AddressDto addressBean = AddressDto.builder().id(address.getId()).street(address.getStreet()).city(address.getCity()).state(address.getState()).country(address.getCountry()) .zip(address.getZip()).build();
			addressesBeans.add(addressBean);
		});
		
	}

	protected void transformClientBeanIntoClient(ClientDto clientBean, List<Loan> listOfLoans, List<Address> addresses) {
		
		clientBean.getLoans().forEach(loanBean -> {
			Loan loan = Loan.builder().loanAccountNumber(loanBean.getLoanAccountNumber()).id(loanBean.getId()).loanType(loanBean.getLoanType()).build();
			List<EMI> emis = new ArrayList<>();
		
			loanBean.getListOfEmis().stream().forEach(emiBean -> {
				EMI emi = EMI.builder().amount(emiBean.getAmount()).dueDate(emiBean.getDueDate()).number(emiBean.getNumber()).loan(loan).build();
				emis.add(emi);
			});
			
			loan.setListOfEmis(emis);
			listOfLoans.add(loan);
		});
		
		clientBean.getAddresses().forEach(addressBean -> {
			Address address = Address.builder().id(addressBean.getId()).street(addressBean.getStreet()).city(addressBean.getCity()).state(addressBean.getState()).country(addressBean.getCountry()).zip(addressBean.getZip()).build();
			addresses.add(address);
		});
		
	}

}
