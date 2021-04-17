/**
 * 
 */
package com.sapient.client.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class Loan implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="LOAN_ACCOUNT_NUMBER",nullable = false)
	private String loanAccountNumber;

	@Column(name="LOAN_TYPE",nullable = false)
	private String loanType;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOAN_ID")
	private List<EMI>listOfEmis;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
	
	
}
