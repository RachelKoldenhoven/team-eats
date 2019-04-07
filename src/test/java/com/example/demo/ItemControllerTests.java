package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	ItemController cut;

	@Autowired
	ItemRepository repository;

	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void before() {
		repository.deleteAll();
	}

	@After
	public void after() {
		repository.deleteAll();
	}


	@Test
	public void shouldCreateNewItem() throws Exception {
		// Setup
		Item expected = new Item(null, "Saag");
		System.out.println(expected);
		String json = mapper.writeValueAsString(expected);
		MockHttpServletRequestBuilder request = post("/api/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		// Exercise
		String response = mvc.perform(request)
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		Item result = mapper.readValue(response, new TypeReference<Item>() { });
		expected.setId(result.getId());

		// Assert
		Item actual = result;
		System.out.println(actual);
		Assert.assertTrue("POST response should match what was in request", expected.equals(actual));

		Item dbActual = repository.findById(actual.getId()).get();
		Assert.assertTrue("Database should match what was in request", expected.equals(dbActual));
	}

}
