package com.example.phi.integration;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class IntegrationTest {
    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    protected TestRestTemplate authenticatedRequest() {
        return testRestTemplate
                .withBasicAuth("user1", "123456");
    }

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
