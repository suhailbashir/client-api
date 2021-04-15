package com.sapient.client.controller;

import static org.mockito.Mockito.when;
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
import com.sapient.client.beans.AddressBean;
import com.sapient.client.beans.ClientBean;
import com.sapient.client.beans.EmiBean;
import com.sapient.client.beans.LoanBean;
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
	public void savClientTest() throws Exception {
		ClientBean bean = createClientBean();
		
		when(clientService.saveClient(bean)).thenReturn(bean);
		
		String url="/client-api/client";
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url)
																	  .contentType(MediaType.APPLICATION_JSON_VALUE)
																	  .accept(MediaType.APPLICATION_JSON)
																	  .characterEncoding("UTF-8")
																	  .content(this.mapper.writeValueAsBytes(bean));
		
		mockMvc.perform(builder).andExpect(status().isCreated());
		
	}

	private ClientBean createClientBean() {

		EmiBean emiBean = EmiBean.builder().id(5L).number(1L).amount(100.0).dueDate(LocalDate.now()).build();

		List<EmiBean> emiBeans = Arrays.asList(emiBean);

		LoanBean loanBean = LoanBean.builder().id(4L).loanAccountNumber("123_LAN_222").loanType("Saving")
				.listOfEmis(emiBeans).build();

		List<LoanBean> loanBeans = Arrays.asList(loanBean);

		AddressBean a1 = AddressBean.builder().id(2L).street("124").State("Rajasthan").Country("India").zip("67676")
				.build();

		AddressBean a2 = AddressBean.builder().id(3L).city("Srinagar").street("12").State("JK").Country("India")
				.zip("8888").build();

		List<AddressBean> addressBeans = Arrays.asList(a1, a2);

		return ClientBean.builder().id(1L).clientName("Suhail").loans(loanBeans).addresses(addressBeans).build();
	}

}
