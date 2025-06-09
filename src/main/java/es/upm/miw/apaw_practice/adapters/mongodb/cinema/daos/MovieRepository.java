package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.MovieEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MovieRepository extends MongoRepository<MovieEntity, String> {
    Optional<MovieEntity> findByTitle(String title);
}