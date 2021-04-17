package com.sapient.client.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.client.dto.AddressDto;
import com.sapient.client.dto.ClientDto;
import com.sapient.client.dto.EmiDto;
import com.sapient.client.dto.LoanDto;
import com.sapient.client.service.ClientService;

@WebMvcTest(value = ClientResource.class)
@ActiveProfiles("default")
@OverrideAutoConfiguration(enabled = true)
public class ClientResourceTest {

	@MockBean
	ClientService clientService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void saveClientTest() throws Exception {
		ClientDto bean = createClientBean();
		
		when(clientService.saveClient(bean)).thenReturn(bean);
		
		String url="/client-api/client";
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url)
																	  .contentType(MediaType.APPLICATION_JSON_VALUE)
																	  .accept(MediaType.APPLICATION_JSON)
																	  .characterEncoding("UTF-8")
																	  .content(this.mapper.writeValueAsBytes(bean));
		
		mockMvc.perform(builder).andExpect(status().isCreated());
		
	}

	

	public  ClientDto createClientBean() {

		EmiDto emiBean = EmiDto.builder().id(5L).number(1L).amount(100.0).dueDate(LocalDate.now()).build();

		List<EmiDto> emiBeans = Arrays.asList(emiBean);

		LoanDto loanBean = LoanDto.builder().id(4L).loanAccountNumber("123_LAN_222").loanType("Saving")
				.listOfEmis(emiBeans).build();

		List<LoanDto> loanBeans = Arrays.asList(loanBean);

		AddressDto a1 = AddressDto.builder().id(2L).street("124").state("Rajasthan").country("India").zip("67676")
				.build();

		AddressDto a2 = AddressDto.builder().id(3L).city("Srinagar").street("12").state("JK").country("India")
				.zip("8888").build();

		List<AddressDto> addressBeans = Arrays.asList(a1, a2);

		return ClientDto.builder().id(1L).clientName("Suhail").loans(loanBeans).addresses(addressBeans).build();
	}

}
