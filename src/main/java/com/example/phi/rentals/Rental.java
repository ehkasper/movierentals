package com.example.phi.rentals;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    @Column(name = "movie_id")
    private long movie_id;

    @Column(name = "created_at")
    private java.sql.Timestamp created_at;

    private String status;

    public Rental() {}

    public Rental(long id, String username, long movie_id, java.sql.Timestamp created_at, String status) {
        this.id = id;
        this.username = username;
        this.movie_id = movie_id;
        this.created_at = created_at;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return id == rental.id && movie_id == rental.movie_id && Objects.equals(username, rental.username) && Objects.equals(created_at, rental.created_at) && Objects.equals(status, rental.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, movie_id, created_at, status);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getMovieId() {
        return movie_id;
    }

    public void setMovieId(long movie_id) {
        this.movie_id = movie_id;
    }

    public java.sql.Timestamp getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(java.sql.Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
