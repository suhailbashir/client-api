package com.sapient.client.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

		List<Loan> listOfLoans = new ArrayList<Loan>();
		List<Address> addresses = new ArrayList<Address>();
		
		List<LoanBean> listOfLoansbeaBeans = new ArrayList<LoanBean>();
		List<AddressBean> addressesBeans = new ArrayList<AddressBean>();
		
		transformClientBean(clientBean, listOfLoans, addresses);
		
		Client client = clientRepository.save(Client.builder()
													.clientName(clientBean.getClientName())
													.loans(listOfLoans)
													.addresses(addresses)
													.build());
		
		transformClient(client, listOfLoansbeaBeans, addressesBeans);

		return ClientBean.builder()
						 .id(client.getId())
						 .clientName(clientBean.getClientName())
						 .loans(listOfLoansbeaBeans)
						 .addresses(addressesBeans)
						 .build();

	}

	private void transformClient(Client client, List<LoanBean> listOfLoansbeaBeans, List<AddressBean> addressesBeans) {
		client.getLoans().forEach(loan -> {
			
			List<EmiBean> emiBeans = new ArrayList<>();

			loan.getListOfEmis().stream().forEach(emi -> {
			
				EmiBean emiBean = EmiBean.builder()
										 .id(emi.getId())
							             .amount(emi.getAmount())
							             .dueDate(emi.getDueDate())
							             .number(emi.getNumber())
							             .build();
				
				emiBeans.add(emiBean);
			});

			LoanBean loanBean = LoanBean.builder()
										.id(loan.getId())
										.loanAccountNumber(loan.getLoanAccountNumber())
										.loanType(loan.getLoanType())
										.listOfEmis(emiBeans)
										.build();
			
			listOfLoansbeaBeans.add(loanBean);
		});

		

		client.getAddresses().forEach(address -> {
			AddressBean addressBean = AddressBean.builder()
									 .id(address.getId())
									 .street(address.getStreet())
									 .city(address.getCity())
									 .State(address.getState())
									 .Country(address.getCountry())
									 .zip(address.getZip())
									 .build();
			
			addressesBeans.add(addressBean);
		});
	}

	private void transformClientBean(ClientBean clientBean, List<Loan> listOfLoans, List<Address> addresses) {
				
		clientBean.getLoans().forEach(loanBean -> {
				
				List<EMI> emis = new ArrayList<>();
		
				loanBean.getListOfEmis().stream().forEach(emiBean -> {
				
					EMI emi = EMI.builder()
								 .amount(emiBean.getAmount())
								 .dueDate(emiBean.getDueDate())
								 .number(emiBean.getNumber())
								 .build();
					
					emis.add(emi);
				});
		
				Loan loan = Loan.builder().loanAccountNumber(loanBean.getLoanAccountNumber())
						.loanType(loanBean.getLoanType()).listOfEmis(emis).build();
				
				listOfLoans.add(loan);
		});
		
		
		
		clientBean.getAddresses().forEach(addressBean -> {
				Address address = Address.builder()
										 .street(addressBean.getStreet())
										 .city(addressBean.getCity())
										 .State(addressBean.getState())
										 .Country(addressBean.getCountry())
										 .zip(addressBean.getZip())
										 .build();
				
				addresses.add(address);
		});
	}

	@Override
	public List<Loan> findLoansOfClient(Long ClientId) {
		Optional<Client> Client = clientRepository.findById(ClientId);
		List<Loan> listOfLoans = new ArrayList<>();
		if (Client.isPresent()) {

			Client.get().getLoans().stream().forEach(loan -> {
				listOfLoans.add(loan);
			});
		}
		return listOfLoans;
	}

	@Override
	public List<Client> findAllClients() {

		List<Client> ListOfClients = clientRepository.findAll();
		return ListOfClients;
	}

	@Override
	public Client updateClient(ClientBean clientBean) {

		log.info("Client bean {}", clientBean);
		Optional<Client> client = clientRepository.findById(clientBean.getId());

		if (client.isPresent()) {
			client.get().setClientName(clientBean.getClientName());

			clientBean.getLoans().stream().forEach(loanBean -> {

				client.get().getLoans().stream().forEach(loann -> {
					if (loanBean.getId() == loann.getId()) {

						loann.setLoanAccountNumber(loanBean.getLoanAccountNumber());
						loanBean.setLoanType(loanBean.getLoanType());

						List<EmiBean> emisBean = loanBean.getListOfEmis();

						List<EMI> emis = client.get().getLoans().stream().flatMap(loan -> loan.getListOfEmis().stream())
								.collect(Collectors.toList());

						emisBean.stream().forEach(emiBean -> {
							emis.stream().forEach(emi -> {
								if (emi.getId() == emiBean.getId()) {
									emi.setAmount(emiBean.getAmount());
									emi.setDueDate(emiBean.getDueDate());
									emi.setNumber(emiBean.getNumber());
								}
							});
						});

					}
				});

			});

			clientBean.getAddresses().stream().forEach(addressBean -> {
				client.get().getAddresses().stream().forEach(ad -> {
					if (ad.getId() == addressBean.getId()) {
						ad.setCity(addressBean.getCity());
						ad.setStreet(addressBean.getStreet());
						ad.setState(addressBean.getState());
						ad.setZip(addressBean.getZip());
						ad.setCountry(addressBean.getCountry());
					}
				});

			});

		} else {
			throw new IllegalArgumentException("Customer with such id doesn't exist.");
		}

		return clientRepository.save(client.get());
	}

	@Override
	public List<Client> deleteClientById(Long id) {

		clientRepository.deleteById(id);
		return findAllClients();
	}

}
