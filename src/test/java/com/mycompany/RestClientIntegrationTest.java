package com.mycompany;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class RestClientIntegrationTest extends Assert {

	RestTemplate template = new RestTemplate();
	
	private static final String BASE_URI = "http://localhost:8080/customers";
	
	@Test
	public void test_get_all_customers_getForObject(){
		List<Customer> list = template.getForObject(BASE_URI, List.class);
		assertNotNull(list);
	}
	
	//ró¿nica pomiêdzy getForObject i getForEntity
	@Test
	public void test_get_all_customers_getForEntity(){
		ResponseEntity<List> response = template.getForEntity(BASE_URI, List.class);
		assertEquals(response.getStatusCode().value(),200);
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void test_delete_operation_failed_exception(){
		ResponseEntity<String> response = template.exchange(BASE_URI, HttpMethod.DELETE, null, String.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void test_delete_operation_success(){
		template.delete(BASE_URI+"/1");
		ResponseEntity<Customer> response = template.getForEntity(BASE_URI+"/1", Customer.class);
		// to rzuci wyj¹tek customer is not found -
	}
	
	
	
	
	
	
	
}
