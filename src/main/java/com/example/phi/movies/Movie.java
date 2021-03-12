package com.example.phi.movies;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    private long id;

    private String title;

    private String director;

    public Movie() {}

    public Movie(long id, String title, String director) {
        this.id = id;
        this.title = title;
        this.director = director;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && Objects.equals(title, movie.title) && Objects.equals(director, movie.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, director);
    }
}
