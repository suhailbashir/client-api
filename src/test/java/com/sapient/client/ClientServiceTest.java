package com.sapient.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class ClientServiceTest {

	@BeforeEach
	public void setup() {
		System.out.println("before each method");
	}
	
	@BeforeAll
	public static void setUpOnce() {
		System.out.println("Once only");
	}
	
	@Test
	@Order(1)
	@DisplayName("TESTING SAVE METHOD")
	public void  testSaveClient() {
		System.out.println("save");
		assertEquals(, null);
		
	}
	
	@Test
	@Order(3)
	@DisplayName("TESTING UPDATE METHOD")
	public void  testUpdateClient() {
		System.out.println("update");
		
	}
	
	@Test
	@Order(2)
	@DisplayName("TESTING GET METHOD")
	public void  testGetClients() {
		System.out.println("get");

	}
	
	@Test
	@Order(4)
	@DisplayName("TESTING GET LOAN METHOD")
	public void  testgetAllLoansOfClient() {
		System.out.println("loans");
		
	}
	
	@Test
	@Order(5)
	@DisplayName("TESTING DELETE METHOD")
	public void  testDeleteClient() {
		System.out.println("delete");
		
	}
	
	@AfterEach
	public void clear() {
		System.out.println("Clear Data");
	}
	
	@AfterAll
	public static void clearAtEndOnce() {
		System.out.println("Clear at the end");
	}
}
