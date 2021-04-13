package com.sapient.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.client.entity.Client;

public interface ClientRepository  extends JpaRepository<Client, Long> {
	
	
}