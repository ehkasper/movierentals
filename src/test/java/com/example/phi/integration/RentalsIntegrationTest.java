package com.example.phi.integration;

import com.example.phi.movies.Movie;
import com.example.phi.movies.MovieRepository;
import com.example.phi.rentals.Rental;
import com.example.phi.rentals.RentalsRepository;
import com.example.phi.users.User;
import com.example.phi.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalsIntegrationTest extends IntegrationTest {

    @Autowired
    RentalsRepository rentalsRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    private Movie movie;
    private User user;

    @BeforeEach
    public void setUp() {
        movie = movieRepository.findByTitle("titanic");
        user = userRepository.findByUsername("user1");
    }

    @Test
    public void shouldTakeOutAMovie() {
        Map<String, String> request = new HashMap<>();
        request.put("movie_id", String.valueOf(movie.getId()));

        ResponseEntity<Rental> response = authenticatedRequest()
                .postForEntity(createURLWithPort("/rentals/takeout"), request, Rental.class);
        Rental rental = response.getBody();

        Rental expected = new Rental(rental.getId(), user.getUsername(), movie.getId(), rental.getCreatedAt(), rental.getStatus());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, rental);
    }


}
