package com.movierental.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersCreationIntegrationTest extends IntegrationTest {

    @Test
    public void shouldPostToUsersAndCreateUser() {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("username", "usertest");
        newUser.put("password", "password");
        ResponseEntity response = testRestTemplate.postForEntity(createURLWithPort("/users"), newUser, String.class);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}
