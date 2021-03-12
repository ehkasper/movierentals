package com.example.phi.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class MoviesController {
    @Autowired
    MovieRepository movieRepository;

    @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Movie>> movies() {
        List<Movie> movies = movieRepository.findAll();
        return ResponseEntity.ok(movies);
    }

    @PostMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Movie> save(@RequestBody MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setDirector(movieRequest.getDirector());
        Movie movieCreated = movieRepository.save(movie);
        return ResponseEntity.accepted().body(movieCreated);
    }

    static class MovieRequest {
        private String title;
        private String director;
        public MovieRequest() {}

        public MovieRequest(String title, String director) {
            this.title = title;
            this.director = director;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }
    }
}
