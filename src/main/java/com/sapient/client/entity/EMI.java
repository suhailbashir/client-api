package com.sapient.client.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author suhail 
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class EMI  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "EMI_NUMBER",nullable = false)
	private Long number;
	
	@Column(name = "EMI_AMOUNT",nullable = false)
	private Double amount;
	
	@Column(name = "DUE_DATE",nullable = false)
	private LocalDate dueDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Loan loan;
	
	
	
}
