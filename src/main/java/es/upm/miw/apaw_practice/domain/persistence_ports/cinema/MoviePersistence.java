package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Movie;

import java.util.List;
import java.util.Optional;

public interface MoviePersistence {
    List<Movie> findAll();
    Optional<Movie> findByTitle(String title);
    Movie save(Movie movie);
    // Agrega aquí otros métodos según tus necesidades
}