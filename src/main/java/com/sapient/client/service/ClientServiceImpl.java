package com.sapient.client.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.client.beans.AddressBean;
import com.sapient.client.beans.ClientBean;
import com.sapient.client.beans.EmiBean;
import com.sapient.client.beans.LoanBean;
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
	public ClientBean saveClient(ClientBean clientBean) {

		List<Loan> listOfLoans = new ArrayList<>();
		List<Address> addresses = new ArrayList<>();
		List<LoanBean> listOfLoanBeans = new ArrayList<>();
		List<AddressBean> addressesBeans = new ArrayList<>();
		
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
		
		return ClientBean.builder().id(savedClient.getId()).clientName(clientBean.getClientName()).loans(listOfLoanBeans).addresses(addressesBeans).build();
	}

	@Override
	public ClientBean findClientById(Long clientId) {
		
			Optional<Client> client = clientRepository.findById(clientId);
			List<LoanBean> listOfLoanBeans = new ArrayList<>();
			List<AddressBean> addressesBeans = new ArrayList<>();
			
			if (client.isPresent()) {
				transformClientIntoClientBean(client.get(), listOfLoanBeans, addressesBeans);
			
			}
		return ClientBean.builder().id(client.get().getId()).clientName(client.get().getClientName()).loans(listOfLoanBeans).addresses(addressesBeans).build();
	}

	@Override
	public List<ClientBean> findAllClients() {
		log.info("ClientServiceImpl::findAllClients::");
		List<Client>listOfClients=clientRepository.findAll();
		List<ClientBean>listOfClientBeans=new ArrayList<>();
		
		listOfClients.stream().forEach(client->{
			
			List<LoanBean> listOfLoanBeans = new ArrayList<>();
			List<AddressBean> addressesBeans = new ArrayList<>();;
			transformClientIntoClientBean(client, listOfLoanBeans, addressesBeans);

			listOfClientBeans.add(ClientBean.builder().id(client.getId()).clientName(client.getClientName()).loans(listOfLoanBeans).addresses(addressesBeans).build());
		}
		
		);
		return listOfClientBeans;
		
	}

	@Override
	public ClientBean updateClient(ClientBean clientBean) {
		
		
		Optional<Client> client = clientRepository.findById(clientBean.getId());
		List<Loan> listOfLoans = new ArrayList<>();
		List<Address> addresses = new ArrayList<>();
		List<LoanBean> listOfLoanBeans = new ArrayList<>();
		List<AddressBean> addressesBeans = new ArrayList<>();
		ClientBean bean=null;
		
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
			bean=ClientBean.builder().id(updatedClient.getId()).clientName(clientBean.getClientName()).loans(listOfLoanBeans).addresses(addressesBeans).build();
		}else {
			throw new RuntimeException("Client with such id doesn't exist");
		}
		return bean;
	}
	
	@Override
	public List<ClientBean> deleteClientById(Long id) {
	
		clientRepository.deleteById(id);
		return findAllClients();
	}
	
	protected void transformClientIntoClientBean(Client client, List<LoanBean> listOfLoanBeans, List<AddressBean> addressesBeans) {
		
		
		client.getLoans().forEach(loan -> {
			List<EmiBean> emiBeans = new ArrayList<>();
			loan.getListOfEmis().stream().forEach(emi -> {
				EmiBean emiBean = EmiBean.builder().id(emi.getId()).amount(emi.getAmount()).dueDate(emi.getDueDate()).number(emi.getNumber()).build();
				emiBeans.add(emiBean);
			});

			LoanBean loanBean = LoanBean.builder().id(loan.getId()).loanAccountNumber(loan.getLoanAccountNumber()).loanType(loan.getLoanType()).listOfEmis(emiBeans).build();
			listOfLoanBeans.add(loanBean);
		});

		client.getAddresses().forEach(address -> {
			AddressBean addressBean = AddressBean.builder().id(address.getId()).street(address.getStreet()).city(address.getCity()).State(address.getState()).Country(address.getCountry()) .zip(address.getZip()).build();
			addressesBeans.add(addressBean);
		});
		
	}

	protected void transformClientBeanIntoClient(ClientBean clientBean, List<Loan> listOfLoans, List<Address> addresses) {
		
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
			Address address = Address.builder().id(addressBean.getId()).street(addressBean.getStreet()).city(addressBean.getCity()).State(addressBean.getState()).Country(addressBean.getCountry()).zip(addressBean.getZip()).build();
			addresses.add(address);
		});
		
	}

}
