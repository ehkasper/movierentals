package com.example.phi.integration;

import com.example.phi.movies.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoviesIntegrationTest extends IntegrationTest {

    @Test
    public void shouldCreateMovies() {
        Map<String, String> newMovie = new HashMap<>();
        newMovie.put("title", "movie1");
        newMovie.put("director", "director1");

        ResponseEntity response = testRestTemplate.postForEntity(createURLWithPort("/movies"), newMovie, Movie.class);

        Movie expected = new Movie();
        expected.setTitle("movie1");
        expected.setDirector("director1");

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }
}
