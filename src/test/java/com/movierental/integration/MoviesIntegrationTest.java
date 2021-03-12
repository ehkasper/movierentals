package com.movierental.integration;

import com.movierental.movies.Movie;
import com.movierental.movies.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoviesIntegrationTest extends IntegrationTest {

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void shouldRetrieveListOfMovies() {
        ResponseEntity<Movie[]> response = authenticatedRequest()
                .getForEntity(createURLWithPort("/movies"), Movie[].class);
        Movie[] movies = response.getBody();

        List<Movie> expected = asList(new Movie(1, "titanic", "james cameron"));
        List<Movie> titanic = Arrays.stream(movies).filter(movie -> movie.getTitle().equals("titanic")).collect(Collectors.toList());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, titanic);
    }

    @Test
    public void shouldCreateMovies() {
        Map<String, String> newMovie = new HashMap<>();
        newMovie.put("title", "movie1");
        newMovie.put("director", "director1");

        ResponseEntity<Movie> response = authenticatedRequest()
                .postForEntity(createURLWithPort("/movies"), newMovie, Movie.class);

        Movie expected = new Movie();
        expected.setTitle("movie1");
        expected.setDirector("director1");

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }
}
