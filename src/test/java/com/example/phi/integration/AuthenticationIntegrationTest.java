package com.example.phi.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationIntegrationTest extends IntegrationTest {

	@Test
	void shouldReturnUnauthorizedWhenGettingSecuredEndpoint() {
		ResponseEntity<Object> response = unauthenticatedRequest().getForEntity(createURLWithPort("/users"), Object.class);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

	@Test
	void shouldReturnAuthorizedWhenSendingUserCredentials() {
		ResponseEntity<Object> response = authenticatedRequest()
				.getForEntity(createURLWithPort("/users"), Object.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	private TestRestTemplate unauthenticatedRequest() {
		return testRestTemplate;
	}
}
