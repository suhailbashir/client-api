package com.sapient.client.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String street;
	private String city;
	private String State;
	private String Country;
	private String zip;

}
