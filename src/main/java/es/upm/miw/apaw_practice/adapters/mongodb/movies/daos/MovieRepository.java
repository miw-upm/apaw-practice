package es.upm.miw.apaw_practice.adapters.mongodb.movies.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.MovieEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MovieRepository extends MongoRepository<MovieEntity, String > {
    Optional<MovieEntity> findByImdbId(String imdbId);
}
