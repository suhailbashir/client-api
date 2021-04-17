package com.sapient.client.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String clientName;
	private List<LoanDto> loans;
	private List<AddressDto> addresses;
}
