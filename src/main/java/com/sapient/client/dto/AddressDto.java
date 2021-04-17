package com.sapient.client.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zip;
}
