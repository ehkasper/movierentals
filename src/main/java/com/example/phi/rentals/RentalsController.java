package com.example.phi.rentals;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestController
public class RentalsController {
    public static final String STATUS_RENT = "RENT";
    @Autowired
    RentalsRepository rentalsRepository;

    @PostMapping(value = "/rentals/takeout", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Rental> save(HttpServletRequest httpServletRequest, @RequestBody RentalRequest rentalRequest) {
        Rental rental = new Rental();
        rental.setUsername(httpServletRequest.getRemoteUser());
        rental.setMovieId(rentalRequest.getMovieId());
        rental.setCreatedAt(java.sql.Timestamp.from(Instant.now()));
        rental.setStatus(STATUS_RENT);

        Rental rentalCreated = rentalsRepository.save(rental);

        return ResponseEntity.ok().body(rentalCreated);
    }

    static class RentalRequest {

        private long movie_id;

        public RentalRequest() {}

        public RentalRequest(long movie_id) {
            this.movie_id = movie_id;
        }

        @JsonProperty("movie_id")
        public long getMovieId() {
            return movie_id;
        }

        public void setMovieId(long movieId) {
            this.movie_id = movieId;
        }
    }
}
