package com.sapient.client.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.client.beans.AddressBean;
import com.sapient.client.beans.ClientBean;
import com.sapient.client.beans.EmiBean;
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
	public Client saveClient(ClientBean clientBean) {
		log.info("Client bean {}", clientBean);
		Client client = new Client();

		List<Loan> listOfLoans = new ArrayList<>();
		List<EMI> listOfEmis = new ArrayList<>();

		clientBean.getLoans().stream()
							 .forEach(custBean -> {
									Loan loan = new Loan();
									custBean.getListOfEmis().stream().forEach(emibean -> {
										EMI emi = new EMI();
										BeanUtils.copyProperties(emibean, emi);
										emi.setLoan(loan);
										listOfEmis.add(emi);
									});

			loan.setLoanAccountNumber(custBean.getLoanAccountNumber());
			loan.setLoanType(custBean.getLoanType());
			loan.setClient(client);
			loan.setListOfEmis(listOfEmis);
			listOfLoans.add(loan);

		});

		List<Address> listOfAddresses = new ArrayList<>();
		List<AddressBean> listOfAddressesBeans = clientBean.getAddresses();

		listOfAddressesBeans.stream()
							.forEach(addessBean -> {
								Address address = new Address();
								BeanUtils.copyProperties(addessBean, address);
								address.setClient(client);
								listOfAddresses.add(address);
							});

		client.setClientName(clientBean.getClientName());
		client.setLoans(listOfLoans);
		client.setAddresses(listOfAddresses);
		Client cust = clientRepository.save(client);
		return cust;
	}

	@Override
	public List<Loan> findLoansOfClient(Long ClientId) {
		Optional<Client> Client = clientRepository.findById(ClientId);
		List<Loan> listOfLoans = new ArrayList<>();
		if (Client.isPresent()) {
			
			Client.get().getLoans().stream()
									.forEach(loan -> {
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
						
						List<EMI> emis = client.get().getLoans().stream()
																.flatMap(loan -> loan.getListOfEmis().stream())
																.collect(Collectors.toList());

						emisBean.stream()
						.forEach(emiBean -> {emis.stream()
												.forEach(emi -> {
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
				client.get().getAddresses().stream()
										   .forEach(ad -> {
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
