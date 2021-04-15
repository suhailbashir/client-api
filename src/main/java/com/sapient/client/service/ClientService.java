package com.sapient.client.service;

import java.util.List;

import com.sapient.client.beans.ClientBean;
import com.sapient.client.beans.LoanBean;

public interface ClientService {
	
	public ClientBean saveClient(ClientBean clientBean);
	public List<LoanBean> findLoansOfClient(Long clientBean);
	public List<ClientBean> findAllClients();
	public ClientBean updateClient(ClientBean clientBean);
	public List<ClientBean>deleteClientById(Long id);
}
