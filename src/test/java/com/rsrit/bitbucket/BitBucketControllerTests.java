package com.rsrit.bitbucket;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rsrit.bitbucket.controller.BitBucketController;
import com.rsrit.bitbucket.model.BitBucketResponse;
import com.rsrit.bitbucket.service.BitBucketService;

@RunWith(MockitoJUnitRunner.class)
public class BitBucketControllerTests {

	private MockMvc mockMvc;

	@Mock
	private BitBucketService bitBucketService;

	BitBucketController bitBucketController;

	private String username = "username";
	private String password = "password";

	@Before
	public void setUp() {
		bitBucketController = new BitBucketController(bitBucketService);
		mockMvc = standaloneSetup(bitBucketController).build();
	}

	@Test
	public void testGetBitBucketResponse() throws Exception {
		when(bitBucketService.getAllRepositories(any(String.class), any(String.class)))
				.thenReturn(new BitBucketResponse(1, "bitBucketData"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getrpos/" + username + "/" + password);

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

		assertEquals("{\"status\":1,\"bitBucketData\":\"bitBucketData\"}", result.getResponse().getContentAsString());
	}
	
}
