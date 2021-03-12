package com.movierental.integration;

import com.movierental.movies.Movie;
import com.movierental.movies.MovieRepository;
import com.movierental.rentals.Rental;
import com.movierental.rentals.RentalsRepository;
import com.movierental.users.User;
import com.movierental.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        Rental expected = new Rental(rental.getId(), user.getUsername(), movie.getId(), rental.getCreatedAt(), "RENT");

        assertNotNull(rental.getCreatedAt());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, rental);
    }

    @Test
    public void shouldReturnAMovie() {
        rentalsRepository.save(new Rental(1, user.getUsername(), movie.getId(), java.sql.Timestamp.from(Instant.now()), "RENT"));
        Rental rentalRequest = rentalsRepository.findAll().get(0);
        Map<String, String> request = new HashMap<>();
        request.put("rental_id", String.valueOf(rentalRequest.getId()));

        ResponseEntity<Rental> response = authenticatedRequest()
                .postForEntity(createURLWithPort("/rentals/return"), request, Rental.class);
        Rental rental = response.getBody();

        Rental expected = new Rental(rentalRequest.getId(), user.getUsername(), movie.getId(), rental.getCreatedAt(), "RETURNED");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, rental);
    }
    @Test
    public void shouldNotReturnAMovieWhenItDoesNotExist() {
        Map<String, String> request = new HashMap<>();
        request.put("rental_id", "randomvalue");

        ResponseEntity response = authenticatedRequest()
                .postForEntity(createURLWithPort("/rentals/return"), request, Object.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
