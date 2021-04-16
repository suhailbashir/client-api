package com.sapient.client.entity;

import java.io.Serializable;

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
public class Address implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="STREET",nullable = false)
	private String street;
	
	@Column(name="CITY",nullable = false)
	private String city;
	
	@Column(name="STATE",nullable = false)
	private String state;
	
	@Column(name="COUNTRY",nullable = false)
	private String country;
	
	@Column(name="ZIP",nullable = false)
	private String zip;
	
	@ManyToOne(cascade  = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID")
	@JsonBackReference
	private Client client;
	
	
	
}
