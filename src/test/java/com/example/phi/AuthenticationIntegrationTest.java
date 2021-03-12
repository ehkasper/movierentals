package com.example.phi;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate testRestTemplate;

	@BeforeEach
	public void setUp() {
		testRestTemplate = new TestRestTemplate();
	}

	@Test
	void shouldReturnUnauthorizedWhenGettingSecuredEndpoint() {
		ResponseEntity<Object> response = testRestTemplate.getForEntity(createURLWithPort("/users"), Object.class);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
