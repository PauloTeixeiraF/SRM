package com.srm.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.srm.test.api.v1.CreditLimitRestController;
import com.srm.test.service.CreditLimitService;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditLimitRestController.class)
public class CreditLimitRestControllerIntegrationTest {

	@Autowired
    private MockMvc mvc;
 
	@MockBean
    private CreditLimitService service;
	
	@SuppressWarnings("unused")
	@Test
	public void createLimit() throws Exception {
	     
	    RequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/create")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Paulo\",\"creditLimit\":" + new BigDecimal(1000) + ",\"risk\":\"B\",\"interestRate\":10}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
    }
}
