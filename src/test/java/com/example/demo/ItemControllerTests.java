package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
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

	private ObjectMapper mapper = new ObjectMapper();


	@Test
	public void shouldCreateNewItem() throws Exception {
		// Setup
		cut.items.clear();
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

		Assert.assertEquals("Memory has only one item", cut.items.size(), 1);

		Item savedItemActual = cut.items.get(0);
		Assert.assertTrue("Memory should match what was in request", expected.equals(savedItemActual));
	}

}
