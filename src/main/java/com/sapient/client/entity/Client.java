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
import javax.persistence.OneToMany;

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
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "CLIENT_NAME", nullable = false)
	private String clientName;

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
	private List<Loan> loans;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "CLIENT_ID")
	private List<Address> addresses;

}
