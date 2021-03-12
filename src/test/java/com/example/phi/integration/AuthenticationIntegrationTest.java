package com.example.phi.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationIntegrationTest extends IntegrationTest {

	@Test
	void shouldReturnUnauthorizedWhenGettingSecuredEndpoint() {
		ResponseEntity<Object> response = testRestTemplate.getForEntity(createURLWithPort("/users"), Object.class);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}
}