package com.sapient.client.beans;

import java.io.Serializable;
import java.util.List;

import com.sapient.client.entity.Address;
import com.sapient.client.entity.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String loanAccountNumber;
	private String loanType;
	private List<EmiBean> listOfEmis;
}
