package com.sapient.client.beans;

import java.io.Serializable;
import java.time.LocalDate;

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
public class EmiBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long number;
	private Double amount;
	private LocalDate dueDate;
}
