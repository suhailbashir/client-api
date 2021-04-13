package com.sapient.client.beans;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String clientName;
	private List<LoanBean> loans;
	private List<AddressBean> addresses;
}
