package com.sapient.client.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmiDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long number;
	private Double amount;
	private LocalDate dueDate;

}
