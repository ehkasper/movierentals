package com.movierental.integration;

import com.movierental.users.User;
import com.movierental.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RepositoryIntegrationTests {

	@Autowired
    UserRepository userRepository;

	@Test
	void shouldBeAbleToConnectoToDatabase() {
		List<User> users = userRepository.findAll();
		assertEquals(1, users.size());
	}

}
