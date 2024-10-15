package es.upm.miw.apaw_practice.domain.services.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Movie;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.MoviePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MoviePersistence moviePersistence;

    @Autowired
    public MovieService(MoviePersistence moviePersistence) {
        this.moviePersistence = moviePersistence;
    }

    public void create(Movie movie) {
        moviePersistence.create(movie);
    }

    public boolean existsImdbId(String imdbId) {
        return moviePersistence.existsImdbId(imdbId);
    }
}
