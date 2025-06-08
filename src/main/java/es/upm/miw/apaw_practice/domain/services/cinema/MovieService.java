package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.MoviePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MoviePersistence moviePersistence;

    @Autowired
    public MovieService(MoviePersistence moviePersistence) {
        this.moviePersistence = moviePersistence;
    }

    public List<Movie> findAll() {
        return moviePersistence.findAll();
    }

    public Movie findByTitle(String title) {
        return moviePersistence.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Movie not found: " + title));
    }

    public Movie create(Movie movie) {
        return moviePersistence.save(movie);
    }
}