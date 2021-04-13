package com.sapient.client.beans;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private UUID loanAccountNumber;
	private String loanType;
	private List<EmiBean> listOfEmis;
}
