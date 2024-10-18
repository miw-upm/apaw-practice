package es.upm.miw.apaw_practice.domain.persistence_ports.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Movie;

import java.util.List;

public interface MoviePersistence {
    void create(Movie movie);

    boolean existsImdbId(String imdbId);
    List<Movie> findByActorRealName(String realName);
}
