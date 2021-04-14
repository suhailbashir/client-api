package com.sapient.client.service;

import java.util.List;

import com.sapient.client.beans.ClientBean;
import com.sapient.client.entity.Client;
import com.sapient.client.entity.Loan;

public interface ClientService {
	
	public ClientBean saveClient(ClientBean customerBean);
	public List<Loan> findLoansOfClient(Long customerId);
	public List<Client> findAllClients();
	public Client updateClient(ClientBean customerBean);
	public List<Client>deleteClientById(Long id);
}
