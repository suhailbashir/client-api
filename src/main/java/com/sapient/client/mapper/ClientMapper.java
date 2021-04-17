package com.sapient.client.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.sapient.client.dto.ClientDto;
import com.sapient.client.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	
	ClientDto mapClientToClientDto(Client client);
	
	@InheritInverseConfiguration
	Client mapClientDtoToClient(ClientDto clientDto);
	
	List<ClientDto>mapToClientDtos(List<Client>client);
	List<Client>mapToClient(List<ClientDto>clientDtos);
}
