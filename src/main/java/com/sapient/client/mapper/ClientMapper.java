package com.sapient.client.mapper;

import org.mapstruct.Mapper;

import com.sapient.client.beans.ClientDto;
import com.sapient.client.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	
	ClientDto mapClientToClientDto(Client client);
	
	Client mapClientDtoToClient(ClientDto clientDto);
}
